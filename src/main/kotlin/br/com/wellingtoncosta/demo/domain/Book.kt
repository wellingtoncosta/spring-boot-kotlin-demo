package br.com.wellingtoncosta.demo.domain

import javax.persistence.*
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST

@Entity
@Table(name = "book")
data class Book(
        @Id
        @GeneratedValue(generator = "book_sequence")
        @SequenceGenerator(name = "book_sequence", sequenceName = "book_id_seq")
        val id: Long? = -1,
        val title: String? = null,
        val year: Long? = null,
        @ManyToOne(cascade = [(PERSIST), (MERGE)])
        @JoinColumn(name = "author")
        val author: Author? = null
)