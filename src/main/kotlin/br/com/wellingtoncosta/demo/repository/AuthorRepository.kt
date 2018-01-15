package br.com.wellingtoncosta.demo.repository

import br.com.wellingtoncosta.demo.domain.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long>