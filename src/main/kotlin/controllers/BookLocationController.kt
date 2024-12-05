package ie.setu.controllers

import ie.setu.models.BookLocation
import ie.setu.models.Location
import ie.setu.persistance.Serializer

/**
 * This class manages the relationship between book and location.
 * This will act as a helper to add books to specific locations.
 */
class BookLocationController(serializerType: Serializer)  {
    /**
     * List that holds book-location pairs. Allows you to add, update and list items.
     */
    private var bookLocations = mutableListOf<BookLocation>()
    private val serializer: Serializer = serializerType

    /**
     * Adds a book to a specific location.
     *
     * @param bookIds-unique ID for the book class.
     * @param locationIds-unique ID for the location class.
     */
    fun addBookToLocation(bookId: Int, locationId: Int) {
        bookLocations.add(BookLocation(bookId, locationId))
    }

    /**
     * Adds a book to a specific location.
     *
     * @param bookIds-unique ID for the book classes.
     * @param locationIds-unique ID for the location classes.
     * the 'for' loop goes through the list of books, one by one.
     * the 'for' loop goes through each location, one by one.
     * 'bookLocations.add(BookLocation(bookId, locationId))' combines the book and location,adding them together.
     */
    fun addManyBooksToManyLocations(bookIds: List<Int>, locationIds: List<Int>) {
        //loops through both lists and adds each book-location combination.
        for (bookId in bookIds) {
            for (locationId in locationIds) {
                bookLocations.add(BookLocation(bookId, locationId))
            }
        }
    }

    /**
     * Adds a book to a specific location.
     *
     * findBooksInLocation will take an input and return a list of book IDs that are within that location.
     * Creating an empty list that stores numbers (from book IDs).
     * the 'for' loop goes through the items stored in the list called bookLocations
     * checks if the current location ID within the list matches the one that is looked for.
     * 'bookLocations.add(BookLocation(bookId, locationId))' combines the book and location,adding them together.
     */

    fun findBooksInLocation(locationId: Int): List<Int> {
        val booksInLocation = mutableListOf<Int>()


        for (bookLocation in bookLocations) {
            if (bookLocation.locationId == locationId){
                booksInLocation.add(bookLocation.bookId)
            }
        }

        return booksInLocation
    }

    @Throws(Exception::class)
    fun load() {
        bookLocations = serializer.read() as ArrayList<BookLocation>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(bookLocations)
    }
}


