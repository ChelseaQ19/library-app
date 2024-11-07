package controllers

import ie.setu.controllers.BookAPI
import ie.setu.models.Book
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BookAPITest {

    private var mysteryBook: Book? = null
    private var sciFiBook: Book? = null
    private var historyBook: Book? = null
    private var fantasyBook: Book? = null
    private var biographyBook: Book? = null
    private var populatedBooks: BookAPI? = BookAPI()
    private var emptyBooks: BookAPI? = BookAPI()

    @BeforeEach
    fun setup(){
        mysteryBook = Book("Agatha Christie", "1111111111", "Mystery of the Nile", 10.99, 250, "Mystery", "English", false)
        sciFiBook = Book("Isaac Asimov", "2222222222", "The Stars, Like Dust", 15.50, 300, "Sci-Fi", "English", false)
        historyBook = Book("Doris Kearns Goodwin", "3333333333", "Team of Rivals", 20.0, 900, "History", "English", false)
        fantasyBook = Book("J.K. Rowling", "4444444444", "Harry Potter", 9.99, 400, "Fantasy", "English", false)
        biographyBook = Book("Walter Isaacson", "5555555555", "Steve Jobs", 12.0, 600, "Biography", "English", false)

        //adding 5 Note to the notes api
        populatedBooks!!.add(mysteryBook!!)
        populatedBooks!!.add(sciFiBook!!)
        populatedBooks!!.add(historyBook!!)
        populatedBooks!!.add(fantasyBook!!)
        populatedBooks!!.add(biographyBook!!)
    }

    @AfterEach
    fun tearDown(){
        mysteryBook = null
        sciFiBook = null
        historyBook = null
        fantasyBook = null
        biographyBook = null
        populatedBooks = null
        emptyBooks = null
    }

    @Test
    fun `adding a Book to a populated list adds to ArrayList`(){
        val newBook = Book("H.G. Wells", "6666666666", "The Invisible Man", 8.99, 280, "Sci-Fi", "English", false)
        assertTrue(populatedBooks!!.add(newBook))
    }

    @Test
    fun `adding a Book to an empty list adds to ArrayList`(){
        val newBook = Book("George Orwell", "7777777777", "1984", 9.99, 300, "Dystopian", "English", false)
        assertTrue(emptyBooks!!.add(newBook))
    }
}
