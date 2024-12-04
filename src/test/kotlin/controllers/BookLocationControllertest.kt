package controllers

import ie.setu.controllers.BookLocationController
import ie.setu.models.Book
import ie.setu.models.Location
import ie.setu.models.BookLocation
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Locale

class BookLocationControllerAPITest {

    private var spiritualBook: Book? = null
    private var crimeBook: Book? = null
    private var location1: Location? = null
    private var location2: Location? = null
    private var bookLocationController: BookLocationController? = null

    @BeforeEach
    fun setup() {
        spiritualBook = Book(1, "Chelsea Quigley", "9999999999", "The Puzzle", 12.99, 300, "Spiritual", "English", false)
        crimeBook = Book(2, "Isaac Asimov", "2222222222", "The Stars, Like Dust", 15.50, 300, "Sci-Fi", "English", false)
        //creating location instances
        location1 = Location(4,1, "Shelf A", 13, false)
        location2 = Location(5,2, "Shelf B", 14, false)

        //creating controller instances
        bookLocationController = BookLocationController()

        //add books to locations check
        bookLocationController!!.addBookToLocation(spiritualBook!!.bookId, location1!!.locationId)
        bookLocationController!!.addBookToLocation(crimeBook!!.bookId, location2!!.locationId)
    }

    @AfterEach
    fun tearDown() {
        spiritualBook = null
        crimeBook = null
        location1 = null
        location2 = null
        bookLocationController = null
    }

    @Nested
    inner class AddBooksToLocations {

        @Test
        fun `adding a Book to multiple locations`() {
            val newBook = Book(8, "Biggie Big", "8888888888", "The Ghost", 16.99, 350, "Non-Fiction", "English", false)
            bookLocationController!!.addBookToLocation(newBook!!.bookId, location1!!.locationId)
            bookLocationController!!.addBookToLocation(newBook!!.bookId, location2!!.locationId)
        }

        @Test
        fun `adding many books to many locations`() {

            val bookIds = listOf(spiritualBook!!.bookId, crimeBook!!.bookId) //list of book IDs created
            val locationIds = listOf(location1!!.locationId, location2!!.locationId) //list of location IDs

            //verification of the books being added to the locations
            //val booksInLocation1 = bookLocationController!!.findBooksInLocation(location1!!.locationId)
            //val booksInLocation2 = bookLocationController!!.findBooksInLocation(location2!!.locationId)

            //Assertions of books being present within the locations provided
            //assertTrue(booksInLocation1.any {it.bookId == spiritualBook!!.bookId})
            //assertTrue(booksInLocation2.any {it.bookId == spiritualBook!!.bookId})
            //assertTrue(booksInLocation1.any {it.bookId == crimeBook!!.bookId})
            //assertTrue(booksInLocation2.any {it.bookId == crimeBook!!.bookId})
        }
    }
}

