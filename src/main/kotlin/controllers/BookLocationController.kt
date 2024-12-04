package ie.setu.controllers

import ie.setu.models.BookLocation

/**
 * This class manages the relationship between book and location.
 * This will act as a helper to add books to specific locations.
 */
class BookLocationController {
    /**
     * List that holds book-location pairs. Allows you to add, update and list items.
     */

    private val bookLocations = mutableListOf<BookLocation>()

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
}
