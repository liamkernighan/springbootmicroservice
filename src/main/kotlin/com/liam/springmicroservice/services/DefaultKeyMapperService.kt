package com.liam.springmicroservice.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class DefaultKeyMapperService : IKeyMapperService {

    @Autowired
    private lateinit var _converter: IKeyConverterService

    private val _sequence = AtomicLong(5L)
    private val _map: MutableMap<Long, String> = ConcurrentHashMap()

    override fun add(link: String): String {
        val id = _sequence.getAndIncrement()
        val key = _converter.idToKey(id)
        _map[id] = link
        return key
    }

    override fun getLink(key: String): IKeyMapperService.Get {
        val id = _converter.keyToId(key)
        return when (val result = _map[id]) {
            is String -> IKeyMapperService.Get.Link(result)
            else -> IKeyMapperService.Get.NotFound(key)
        }
    }
}
