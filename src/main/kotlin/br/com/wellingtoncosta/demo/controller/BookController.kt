package br.com.wellingtoncosta.demo.controller

import br.com.wellingtoncosta.demo.domain.Book
import br.com.wellingtoncosta.demo.repository.BookRepository
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/books")
class BookController(private val repository: BookRepository) {

    @ResponseStatus(OK)
    @GetMapping(produces = [APPLICATION_JSON_UTF8_VALUE])
    fun getAll(): Iterable<Book> = repository.findAll()

    @ResponseStatus(CREATED)
    @PostMapping(consumes = [(APPLICATION_JSON_UTF8_VALUE)], produces = [APPLICATION_JSON_UTF8_VALUE])
    fun save(@RequestBody book: Book): Book = repository.save(book)

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<List<Book>> {
        repository.deleteById(id)
        return ResponseEntity(NO_CONTENT)
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    fun deleteAll(): ResponseEntity<Book> {
        repository.deleteAll()
        return ResponseEntity(NO_CONTENT)
    }

}