package com.liam.springmicroservice.services

interface IKeyConverterService {
    fun idToKey(id: Long): String
    fun keyToId(key: String): Long
}
