package br.com.wellingtoncosta.demo

import br.com.wellingtoncosta.demo.web.AuthorControllerTest
import br.com.wellingtoncosta.demo.web.BookControllerTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite::class)
@SuiteClasses(
        AuthorControllerTest::class,
        BookControllerTest::class
)
class TestSuite