package controllers

import models.BookLocation

/**
 * This class manages the relationship between book and location.
 * This will act as a helper to add a book to a specific location.
 */
class BookLocationController {

    //list that holds book-location pairs. Allows you to add, update and list items within the list.
    private val bookLocations = mutableListOf<BookLocation>()

    /**
     * Adds a book to a specific location.
     *
     * @param bookId-unique ID for the book class.
     * @param locationId-unique ID for the location class.
     */
    fun addBookToLocation(bookId: Int, locationId: Int) {
        bookLocations.add(BookLocation(bookId, locationId))
    }
    /**
     * Lists all the books to a specific location.
     *
     * @param locationId the ID of the location to search for.
     * @return a list of books stored at that specific location.
     */

    fun listBooksinLocations(locationId: Int) = bookLocations.filter { it.locationId == locationId }

}