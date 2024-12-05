package ie.setu.controllers


import ie.setu.models.Book
import ie.setu.models.Location
import ie.setu.models.BookLocation
import ie.setu.persistance.Serializer

class LocationAPI(serializerType: Serializer)  {

    private var locations = ArrayList<Location>()
    private var bookLocations = ArrayList<BookLocation>()
    private val serializer: Serializer = serializerType

    fun add(location: Location): Boolean { //adding a book to our application
        return locations.add(location)
    }

    /**
     * Adds multiple books to multiple locations in the library.
     * This method takes two lists: one for book IDs and one for location IDs.
     * It loops through both lists and associates each book with each location,
     * creating a new BookLocation entry for each combination.
     *
     * @param bookIds a list of book IDs to be added to locations.
     * @param locationIds a list of location IDs where books will be added.
     * @return true if the books were successfully added to the locations.
     */
    fun addBookToLocation(bookIds: List<Int>, locationIds: List<Int>) {

        for (locationId in locationIds) {


            for (bookId in bookIds) {


                val bookLocation = BookLocation(bookId, locationId)

                bookLocations.add(bookLocation)

            }
        }
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

    @Throws(Exception::class)
    fun load() {
        locations = serializer.read() as ArrayList<Location>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(locations)
    }
}