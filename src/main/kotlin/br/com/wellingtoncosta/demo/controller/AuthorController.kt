package br.com.wellingtoncosta.demo.controller

import br.com.wellingtoncosta.demo.domain.Author
import br.com.wellingtoncosta.demo.repository.AuthorRepository
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/authors")
class AuthorController(private val repository: AuthorRepository) {

    @ResponseStatus(OK)
    @GetMapping(produces = [APPLICATION_JSON_UTF8_VALUE])
    fun getAll(): Iterable<Author> = repository.findAll()

    @ResponseStatus(CREATED)
    @PostMapping(consumes = [(APPLICATION_JSON_UTF8_VALUE)], produces = [APPLICATION_JSON_UTF8_VALUE])
    fun save(@RequestBody author: Author): Author = repository.save(author)

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<List<Author>> {
        repository.deleteById(id)
        return ResponseEntity(NO_CONTENT)
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    fun deleteAll(): ResponseEntity<Author> {
        repository.deleteAll()
        return ResponseEntity(NO_CONTENT)
    }

}