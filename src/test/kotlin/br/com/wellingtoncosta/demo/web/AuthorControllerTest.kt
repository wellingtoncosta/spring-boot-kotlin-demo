package br.com.wellingtoncosta.demo.web

import br.com.wellingtoncosta.demo.domain.Author
import br.com.wellingtoncosta.demo.repository.AuthorRepository
import org.junit.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import sun.plugin2.util.PojoUtil.toJson
import javax.inject.Inject

class AuthorControllerTest : BaseControllerTest() {

    @Inject
    private lateinit var authorRepository: AuthorRepository

    private val apiEndpoint: String = "/api/authors"

    private var danBrown: Author = Author(null, "Dan Brown")

    @Test
    fun getAllAuthorsWithEmptyTable() {
        givenThatHaveNoAuthors()
        whenFetchAllAuthors()
        thenReceiveAEmptyList()
    }

    @Test
    fun getAllAuthorsWithPopulatedTable() {
        givenThatHaveNoAuthors()
        givenThatHaveOneAuthorAlreadySaved()
        whenFetchAllAuthors()
        thenReceiveAListWithSize(1)
    }

    @Test
    fun saveNewAuthor() {
        givenThatHaveNoAuthors()
        whenSaveTheAuthor(danBrown)
        thenTheSavedAuthorMustHaveName(danBrown.name!!)
    }

    @Test
    fun deleteAuthor() {
        givenThatHaveNoAuthors()
        givenThatHaveOneAuthorAlreadySaved()
        whenTryToDeleteTheAuthor()
        thenReceiveStatus(HttpStatus.NO_CONTENT)
    }

    private fun givenThatHaveNoAuthors() {
        authorRepository.deleteAll()
    }

    private fun givenThatHaveOneAuthorAlreadySaved() {
        this.danBrown = authorRepository.save(danBrown)
    }

    private fun whenFetchAllAuthors() {
        request = get(apiEndpoint)
    }

    private fun whenSaveTheAuthor(author: Author) {
        request = post(apiEndpoint)
                .content(toJson(author))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
    }

    private fun whenTryToDeleteTheAuthor() {
        val (id) = danBrown
        request = delete("$apiEndpoint/$id")
    }

    protected fun thenTheSavedAuthorMustHaveName(name: String) {
        mvc.perform(request)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(name))
    }

}