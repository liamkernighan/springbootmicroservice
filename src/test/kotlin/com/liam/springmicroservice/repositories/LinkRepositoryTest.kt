package com.liam.springmicroservice.repositories

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.liam.springmicroservice.models.AbstractRepositoryTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = [LinkRepositoryTest.DATASET])
open class LinkRepositoryTest : AbstractRepositoryTest {

    companion object {
        const val DATASET = ""
        const val LINK_1_ID = ""
        const val LINK_1_TEXT = ""
    }

    @Autowired
    lateinit var repository: LinkRepository

    @Test
    fun findOneExisting() {
        val got = repository.findOne(LINK_1_ID)
        assertNotNull(got)
        assertEquals(link, Link(LINK_1_TEXT, LINK_1_ID))
    }

    @Test findOneNotExisting() {

    }
}