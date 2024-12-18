package controllers

import ie.setu.controllers.BookLocationController
import ie.setu.models.Book
import ie.setu.models.Location
import ie.setu.persistance.XMLSerializer
import ie.setu.persistance.JSONSerializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import java.util.Locale

/**
 * Test class for testing the functionality of the [BookLocationController]
 * This class contains tests for adding books to locations, and for handling multiple books and locations.
 * AddingBookController to test the controller that was created.
 */
class BookLocationControllerAPITest {


    private var spiritualBook: Book? = null
    private var crimeBook: Book? = null
    private var location1: Location? = null
    private var location2: Location? = null
    private var bookLocationController: BookLocationController? = null

    /**
     * Creating two books and two locations to test together.
     * Creating an instance of the [BookLocationController], which manages the relationship between book and location.
     * Adding the two books to their corresponding locations, using {addBookToLocation}
     * (Using a similar layout to the other JUnit tests.
     */

    @BeforeEach
    fun setup() {
        spiritualBook = Book(1, "Chelsea Quigley", "9999999999", "The Puzzle", 12.99, 300, "Spiritual", "English", false)
        crimeBook = Book(2, "Isaac Asimov", "2222222222", "The Stars, Like Dust", 15.50, 300, "Sci-Fi", "English", false)
        //creating location instances
        location1 = Location(4,1, "Shelf A", 13, false)
        location2 = Location(5,2, "Shelf B", 14, false)

        //creating controller instances
        bookLocationController = BookLocationController(XMLSerializer(File("bookLocations.xml")))

        /**
         * Takes the ID of the spiritual book[spiritualBook.bookId] and assigns it to the ID of the first location [location1.locationId]
         * Takes the ID of the crime book[crimeBook.bookId] and assigns it to the ID of the second location [location2.locationId]
         */

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

        /**
         * Creating a new book and adding it to two locations (location1, location2), using the method of {addBookToLocation}
         */
        @Test
        fun `adding a book to many locations`() {
            val newBook = Book(8, "Biggie Big", "8888888888", "The Ghost", 16.99, 350, "Non-Fiction", "English", false)
            bookLocationController!!.addBookToLocation(newBook!!.bookId, location1!!.locationId)
            bookLocationController!!.addBookToLocation(newBook!!.bookId, location2!!.locationId)
        }

        @Test
        fun `adding many books to many locations`() {

            val bookIds = listOf(spiritualBook!!.bookId, crimeBook!!.bookId) //list of book IDs created
            val locationIds = listOf(location1!!.locationId, location2!!.locationId) //list of location IDs

        }
    }

    @Nested
    inner class FindBooksInLocations {

        /**
         * Creating a new method to find the book in the specified location.
         * [findBooksInLocation]- new method name for finding the testing book in specified location.
         */
        @Test
        fun `finding a book in a location`() {
            val newBook =
                Book(9, "Diarmuid O' Connor", "7777771111", "The Test", 20.99, 500, "Non-Fiction", "English", false)
            bookLocationController!!.addBookToLocation(newBook!!.bookId, location1!!.locationId)


            val booksInLocation1 = bookLocationController!!.findBooksInLocation(location1!!.locationId)
            val booksInLocation2 = bookLocationController!!.findBooksInLocation(location2!!.locationId)


            assertTrue(booksInLocation1.contains(spiritualBook!!.bookId), "Spiritual Book is in location1")
            assertTrue(booksInLocation2.contains(crimeBook!!.bookId), "Crime Book is in location1")
        }

        /**
         * Removed assertions that did not apply to this specific controller test (BookLocation does not have number of...)
         * Tests to make sure the books mentioned are in their correct location as intended.
         */

        @Nested
        inner class PersistenceTests {

            @Test
            fun `saving and loading an empty collection in XML doesn't crash app`() {

                val storingBookLocations =BookLocationController(XMLSerializer(File("bookLocation.xml")))
                storingBookLocations.store()


                val loadedBookLocations = BookLocationController(XMLSerializer(File("bookLocation.xml")))
                loadedBookLocations.load()

            }

            @Test
            fun `saving and loading an loaded collection in XML doesn't loose data`() {

                val storingBookLocations = BookLocationController(XMLSerializer(File("bookLocations.xml")))
                storingBookLocations.addBookToLocation(spiritualBook!!.bookId, location1!!.locationId)
                storingBookLocations.addBookToLocation(crimeBook!!.bookId, location2!!.locationId)
                storingBookLocations.store()


                val loadedBookLocations = BookLocationController(XMLSerializer(File("bookLocations.xml")))
                loadedBookLocations.load()

                assertTrue(loadedBookLocations.findBooksInLocation(location1!!.locationId).contains(spiritualBook!!.bookId))
                assertTrue(loadedBookLocations.findBooksInLocation(location2!!.locationId).contains(crimeBook!!.bookId))

            }
        }

        @Test
        fun `saving and loading an empty collection in JSON doesn't crash app`() {

            val storingBookLocations =BookLocationController(JSONSerializer(File("bookLocation.json")))
            storingBookLocations.store()


            val loadedBookLocations = BookLocationController(JSONSerializer(File("bookLocation.json")))
            loadedBookLocations.load()

        }

        @Test
        fun `saving and loading an loaded collection in JSON doesn't loose data`() {

            val storingBookLocations = BookLocationController(JSONSerializer(File("bookLocations.json")))
            storingBookLocations.addBookToLocation(spiritualBook!!.bookId, location1!!.locationId)
            storingBookLocations.addBookToLocation(crimeBook!!.bookId, location2!!.locationId)
            storingBookLocations.store()


            val loadedBookLocations = BookLocationController(JSONSerializer(File("bookLocations.json")))
            loadedBookLocations.load()

            assertTrue(loadedBookLocations.findBooksInLocation(location1!!.locationId).contains(spiritualBook!!.bookId))
            assertTrue(loadedBookLocations.findBooksInLocation(location2!!.locationId).contains(crimeBook!!.bookId))

        }
    }

}

