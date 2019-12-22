package com.liam.springmicroservice.controllers

import com.liam.springmicroservice.SpringmicroserviceApplication
import com.liam.springmicroservice.services.IKeyMapperService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [SpringmicroserviceApplication::class])
@WebAppConfiguration
class RedirectControllerTest {

    private val _badPath = "notFound"
    private val _redirectStatus = 302
    private val _path = "meow"
    private val _headerName = "Location"
    private val _headerValue = "http://ya.ru"

    @Mock
    lateinit var _service: IKeyMapperService

    @Autowired
    lateinit var _webApplicationContext: WebApplicationContext

    @Autowired
    @InjectMocks
    lateinit var _controller: RedirectController

    lateinit var _mockMvc: MockMvc

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        defineMockControllerBehaviour()
        _mockMvc = MockMvcBuilders
                .webAppContextSetup(_webApplicationContext)
                .build()
    }

    private fun defineMockControllerBehaviour() {
        Mockito.`when`(_service.getLink(_path)).thenReturn(IKeyMapperService.Get.Link(_headerValue))
        Mockito.`when`(_service.getLink(_badPath)).thenReturn(IKeyMapperService.Get.NotFound(_badPath))
    }

    @Test
    fun controllerShouldRedirect_IfRequestIsSuccessful() {
        _mockMvc.perform(MockMvcRequestBuilders.get("/$_path"))
                .andExpect(status().`is`(_redirectStatus))
                .andExpect(header().string(_headerName, _headerValue))
    }

    @Test
    fun controllerShouldReturn404_ForWrongKey() {
        _mockMvc.perform(MockMvcRequestBuilders.get("/$_badPath"))
                .andExpect(status().`is`(404))
    }
}