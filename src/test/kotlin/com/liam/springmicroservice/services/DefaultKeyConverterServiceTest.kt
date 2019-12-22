package com.liam.springmicroservice.services

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.math.absoluteValue

class DefaultKeyConverterServiceTest {

    private val service: IKeyConverterService = DefaultKeyConverterService()

    @Test
    fun givenId_MustBeConvertibleBothWays() {
        val rand = java.util.Random()
        (0..50).map {
            val initialId = rand.nextLong().absoluteValue
            val link = service.idToKey(initialId)
            val reversedId = service.keyToId(link)
            assertEquals(initialId, reversedId)
        }

    }

}