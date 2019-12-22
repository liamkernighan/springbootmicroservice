package com.liam.springmicroservice.services

import org.springframework.stereotype.Component

@Component
class DefaultKeyConverterService : IKeyConverterService {

    companion object {
        private val chars = "abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLNMOPQRSTUVWXYZ0123456789-".toCharArray()
    }

    override fun idToKey(id: Long): String {

        fun Long.sizeMod() = (this % chars.size).toInt()

        tailrec fun idToKeyRecursive(id: Long, result: Array<Int> = emptyArray()): Array<Int> = when (id) {
            0L -> result + id.sizeMod()
            else -> idToKeyRecursive(id / chars.size, result + id.sizeMod())
        }

        val reversedChars = idToKeyRecursive(id)
                .map { chars[it] }
                .reversed()
                .toCharArray()

        return String(reversedChars)
    }

    override fun keyToId(key: String) = key
            .toCharArray()
            .fold(0L, { acc, char -> acc * chars.size + chars.indexOf(char) })
}