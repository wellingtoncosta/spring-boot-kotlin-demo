package br.com.wellingtoncosta.demo.domain

import javax.persistence.*

@Entity
@Table(name = "author")
data class Author(
        @Id
        @GeneratedValue(generator = "author_sequence")
        @SequenceGenerator(name = "author_sequence", sequenceName = "author_id_seq")
        val id: Long? = -1,
        val name: String? = null
)