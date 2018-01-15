package br.com.wellingtoncosta.demo.repository

import br.com.wellingtoncosta.demo.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long>