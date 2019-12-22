package com.liam.springmicroservice.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DefaultKeyMapperServiceTest {

    private val _linkA = "http://ya.ru"
    private val _linkB = "http://google.ru"
    private val _keyA = "keyA"
    private val _keyB = "keyB"
    private  val _idA = 5L
    private val _idB = 6L

    @InjectMocks
    private val _service = DefaultKeyMapperService()

    @Mock
    private lateinit var _converter: IKeyConverterService

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(_converter.keyToId(_keyA)).thenReturn(_idA)
        Mockito.`when`(_converter.idToKey(_idA)).thenReturn(_keyA)
        Mockito.`when`(_converter.keyToId(_keyB)).thenReturn(_idB)
        Mockito.`when`(_converter.idToKey(_idB)).thenReturn(_keyB)
    }

    @Test
    fun clientCanAddLinks() {
        val keyA = _service.add(_linkA)
        assertEquals(IKeyMapperService.Get.Link(_linkA), _service.getLink(keyA))

        val keyB = _service.add(_linkB)
        assertEquals(IKeyMapperService.Get.Link(_linkB), _service.getLink(keyB))
        assertNotEquals(keyA, keyB)
    }

    @Test
    fun clientCannotTakeLink_IfKeyWasNotFoundInService() {
        assertEquals(IKeyMapperService.Get.NotFound(_keyA), _service.getLink(_keyA))
    }
}