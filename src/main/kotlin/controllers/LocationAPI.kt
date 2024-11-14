package ie.setu.controllers


import ie.setu.models.Location

class LocationAPI {

    private var locations = ArrayList<Location>()

    fun add(location: Location): Boolean { //adding a book to our application
        return locations.add(location)
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