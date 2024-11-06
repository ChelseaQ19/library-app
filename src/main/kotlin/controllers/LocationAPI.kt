package ie.setu.controllers

import ie.setu.models.Location

class LocationAPI {

    private var locations = ArrayList<Location>()

    fun add(location: Location): Boolean { //adding a book to our application
        return locations.add(location)
    }

    fun listAllLocations(): String { //lists all books that are stored in the books list.
        return if (locations.isEmpty()) {
            "No books stored"
        } else {
            var listOfBooks = ""
            for (i in locations.indices) {
                listOfBooks += "${i}: ${locations[i]} \n"
            }
            listOfBooks
        }
    }
}