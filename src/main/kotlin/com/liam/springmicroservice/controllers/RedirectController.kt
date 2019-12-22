package com.liam.springmicroservice.controllers

import com.liam.springmicroservice.services.IKeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
class RedirectController {

    @Autowired
    private lateinit var _service: IKeyMapperService

    companion object {
        private const val HEADER_NAME = "Location"
    }

    @RequestMapping("/")
    fun home(): String {
        return "Home"
    }

    @RequestMapping("/{key}")
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {

        when (val result = _service.getLink(key)) {
             is IKeyMapperService.Get.Link -> {
                response.setHeader(HEADER_NAME, result.link)
                response.status = 302
            }
            else -> {
                response.status = 404
            }
        }
    }
}