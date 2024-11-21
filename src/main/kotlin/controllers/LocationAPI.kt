package ie.setu.controllers


import ie.setu.models.Location
import ie.setu.models.BookLocation

class LocationAPI {

    private var locations = ArrayList<Location>()
    private var bookLocations = ArrayList<BookLocation>()

    fun add(location: Location): Boolean { //adding a book to our application
        return locations.add(location)
    }
    /**
     *Associates a book with a location in the library.
     *@paramId bookId the book ID to add to the location.
     *@paramId locationId the location ID where the book is to be added.
     */
    fun addBookToLocation(bookId: Int, locationId: Int): Boolean {

        val bookLocation = BookLocation(bookId, locationId)

        bookLocations.add(bookLocation)
        
        return true
    }

    fun listAllLocations(): String { //lists all books that are stored in the books list.
        return if (locations.isEmpty()) {
            "No locations stored"
        } else {
            var listOfBooks = ""
            for (i in locations.indices) {
                listOfBooks += "${i}: ${locations[i]} \n"
            }
            listOfBooks
        }
    }

    fun numberOfLocations(): Int {
        return locations.size
    }

    fun findLocation(index: Int): Location? {
        return if (isValidListIndex(index, locations)) {
            locations[index]
        } else null
    }

    //utility method to determine if an index is valid in a list.
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}