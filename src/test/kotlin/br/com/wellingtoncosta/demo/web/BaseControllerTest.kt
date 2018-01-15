package br.com.wellingtoncosta.demo.web

import org.flywaydb.test.FlywayTestExecutionListener
import org.flywaydb.test.annotation.FlywayTest
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import javax.inject.Inject

@FlywayTest
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner::class)
@TestExecutionListeners(
        DependencyInjectionTestExecutionListener::class,
        FlywayTestExecutionListener::class
)
class BaseControllerTest {

    @Inject
    protected lateinit var context: WebApplicationContext

    protected lateinit var mvc: MockMvc

    protected lateinit var request: MockHttpServletRequestBuilder

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    protected fun thenReceiveAEmptyList() {
        mvc.perform(request)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isEmpty)
    }

    protected fun thenReceiveAListWithSize(size: Int) {
        mvc.perform(request)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize<Int>(size)))
    }

    protected fun thenReceiveStatus(httpStatus: HttpStatus) {
        mvc.perform(request)
                .andExpect(status().isNoContent)
    }

}