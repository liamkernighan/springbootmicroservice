package com.liam.springmicroservice.models

import com.github.springtestdbunit.DbUnitTestExecutionListener
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringRunner

@TestExecutionListeners(DbUnitTestExecutionListener::class)
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext
abstract class AbstractRepositoryTest : AbstractTransactionalJUnit4SpringContextTests() {

}