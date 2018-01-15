package br.com.wellingtoncosta.demo.web

import br.com.wellingtoncosta.demo.domain.Author
import br.com.wellingtoncosta.demo.domain.Book
import br.com.wellingtoncosta.demo.repository.AuthorRepository
import br.com.wellingtoncosta.demo.repository.BookRepository
import org.junit.Test
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import sun.plugin2.util.PojoUtil
import sun.plugin2.util.PojoUtil.toJson
import javax.inject.Inject

class BookControllerTest : BaseControllerTest() {

    @Inject
    private lateinit var bookRepository: BookRepository

    @Inject
    private lateinit var authorRepository: AuthorRepository

    private val apiEndpoint: String = "/api/books"

    private final val danBrown: Author = Author(null, "Dan Brown")

    private var angelsAndDemons: Book = Book(null, "Angels & Demons", 2000, danBrown)

    private val inferno: Book = Book(null, "Inferno", 2013, danBrown)

    @Test
    fun getAllBooksWithEmptyTable() {
        givenThatHaveNoBooks()
        whenFetchAllBooks()
        thenReceiveAEmptyList()
    }

    @Test
    fun getAllBooksWithPopulatedTable() {
        givenThatHaveNoBooks()
        whenSaveBooks(listOf(angelsAndDemons, inferno))
        whenFetchAllBooks()
        thenReceiveAListWithSize(2)
    }

    @Test
    fun saveNewBook() {
        givenThatHaveNoBooks()
        whenSaveTheBook(angelsAndDemons)
        thenTheSavedBookMustHaveTitle(angelsAndDemons.title!!)
    }

    @Test
    fun saveAnotherNewBook() {
        givenThatHaveNoBooks()
        whenSaveTheBook(inferno)
        thenTheSavedBookMustHaveTitle(inferno.title!!)
    }

    @Test
    fun deleteBook() {
        givenThatHaveNoBooks()
        givenThatHaveOneBookAlreadySaved()
        whenTryToDeleteTheBook()
        thenReceiveStatus(NO_CONTENT)
    }

    private fun givenThatHaveNoBooks() {
        bookRepository.deleteAll()
        authorRepository.deleteAll()
    }

    private fun givenThatHaveOneBookAlreadySaved() {
        this.angelsAndDemons = bookRepository.save(angelsAndDemons)
    }

    private fun whenFetchAllBooks() {
        request = get(apiEndpoint)
    }

    private fun whenSaveTheBook(book: Book) {
        request = post(apiEndpoint)
                .content(toJson(book))
                .contentType(APPLICATION_JSON_UTF8)
    }

    private fun whenSaveBooks(books: List<Book>) {
        bookRepository.saveAll(books)
    }

    private fun whenTryToDeleteTheBook() {
        val (id) = angelsAndDemons
        request = delete("$apiEndpoint/$id")
    }

    protected fun thenTheSavedBookMustHaveTitle(title: String) {
        mvc.perform(request)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.title").value(title))
    }

}