package com.liam.springmicroservice.services

interface IKeyMapperService {

    fun add(link: String): String
    fun getLink(key: String): Get

    interface Get {
        data class Link(val link: String) : Get
        data class NotFound(val key: String) : Get
    }

}
