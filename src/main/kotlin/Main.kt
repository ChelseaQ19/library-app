package ie.setu

import ie.setu.controllers.BookAPI
import ie.setu.controllers.LocationAPI
import ie.setu.controllers.BookLocationController
import ie.setu.models.Book
import ie.setu.models.Location
import ie.setu.utils.readNextDouble
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextLine
import io.github.oshai.kotlinlogging.KotlinLogging
import ie.setu.persistance.XMLSerializer
import ie.setu.persistance.JSONSerializer
import java.io.File
import java.lang.System.exit

private val logger = KotlinLogging.logger {}
private val bookAPI = BookAPI(JSONSerializer(File("books.json")))
private val locationAPI = LocationAPI(JSONSerializer(File("locations.json")))
private val bookLocationController = BookLocationController(JSONSerializer(File("bookLocations.json")))

/**
 * Starting point for the library management application.
 * Starts the application by running the main menu.
 */
fun main() {
    runMenu()
}

/**
 * Displays the main menu option for the library system and reads in user input.
 * @return the option selected by the user an [Int].
 */
fun mainMenu() : Int {
    print("""
    > ----------------------------------
    > |    LIBRARY MANAGEMENT APP      |
    > ----------------------------------
    > | LIBRARY MENU                   |
    > |   1) Add a book                |
    > |   2) List all books            |
    > |   3) Update a book             |
    > |   4) Delete a book             |
    > |   5) Add location              |
    > |   6) List all locations        |
    > |   7) Add books to locations    |
    > |   8) Find books in location    |  
    > |   9) Save books                |
    > |   10)Load books                |
    > |   11)Save locations            |
    > |   12)Load locations            |
    > |   13)Save books in locations   |
    > |   12)Load books in locations   |
    > ----------------------------------
    > |   0) Exit                      |
    > ----------------------------------
    ==>> """.trimMargin(">"))
    return readNextInt(" > ==>>")
}

/**
 * Controls the application flow, continuously displaying the menu until the user exits the application.
 */
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addBook()
            2  -> listBooks()
            3  -> updateBook()
            4  -> deleteBook()
            5  -> addLocation()
            6 -> listLocations()
            7 -> addManyBooksToManyLocations()
            8 -> findBooksInLocation()
            9 -> save()
            10 ->load()
            11 ->saveLocations()
            12 ->loadLocations()
            13 ->saveBookLocations()
            14 ->loadBookLocations()
            0  -> exitApp()
            else -> println("Invalid option entered: ${option}")
        }
    } while (true)
}

/**
 * Adds a new [Book] to the library.
 * Prompts the user for book details and lets user know if that has been successful or not.
 */

fun addBook(){
    val bookId = readNextInt("Enter the ID of the book: ")
    val bookAuthor = readNextLine("Enter the author of the book: ")
    val bookISBN = readNextLine("Enter the ISBN of the book: ")
    val bookTitle = readNextLine("Enter the title of the book:")
    val bookPrice = readNextDouble("Enter the price of the book:")
    val bookPages = readNextInt("Enter the number of pages for the book:")
    val bookGenre = readNextLine("Enter the genre of the book:")
    val bookLanguage = readNextLine("Enter the language of the book:")
    val isAdded = bookAPI.add(Book(bookId, bookAuthor, bookISBN, bookTitle, bookPrice, bookPages, bookGenre, bookLanguage,false))

    if (isAdded) {
        println("Added Successfully to library")
    } else {
        println("Add Failed to library")
    }
}

/**
 * Lists all books stored in the library.
 * Calls [bookAPI.listAllBooks] to retrieve and display the list.
 */
fun listBooks(){
    println(bookAPI.listAllBooks())
}

/**
 * Updates an existing book in the library.
 * A placeholder that logs when invoked.
 */
fun updateBook(){
    logger.info { "updateBook() function invoked" }
}

/**
 * Deletes a book from the library.
 * A placeholder that logs when invoked.
 */
fun deleteBook(){
    listBooks()
    if (bookAPI.numberOfBooks() > 0) {
        //only ask the user to choose the book to delete if books exist
        val indexToDelete = readNextInt("Enter the index of the book to delete: ")
        //pass the index of the book to BookAPI for deleting and check for success.
        val bookToDelete = bookAPI.deleteBook(indexToDelete)
        if (bookToDelete != null) {
            println("Delete Successful! Deleted book: ${bookToDelete.bookTitle}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun addLocation(){
    val locationId = readNextInt("Enter the ID of the book location: ")
    val locationAisle = readNextInt("Enter the aisle number of the book location: ")
    val locationShelf = readNextLine("Enter the shelf of the book location:")
    val locationIndex = readNextInt("Enter the index number of the book location:")
    val isAdded = locationAPI.add(Location(locationId,locationAisle, locationShelf, locationIndex, false))

    if (isAdded) {
        println("Added Successfully to library")
    } else {
        println("Add Failed to library")
    }
}

fun listLocations(){
    logger.info {"listLocations() function invoked"}
}

/**
 * Using the mapping format (public inline fun <T, R> Array<out T>.map(transform: (T) -> R): List<R> {)-Kotloin Offical website
 * Splitting the input (splitting the string)
 * Processing each item (converting each piece of string into a number format for results)
 * Returning the new collection (creates a new list of numbers, which are the book IDs)
 * following the same pattern as the 'addBook' method
 */

fun addManyBooksToManyLocations() {
    //Asks the user for book ID's
    println("Enter the IDs of the books (comma seperated): ")
    val booksInput = readNextLine(" > ")
    val bookIds = booksInput.split(",").map { it.trim().toInt() }

    //Asks the user for location ID's
    println("Enter the IDs of the locations (comma seperated): ")
    val locationsInput = readNextLine(" > ")
    val locationIds = locationsInput.split(",").map { it.trim().toInt() }

    //calling the method from the BookLocationController
    bookLocationController.addManyBooksToManyLocations(bookIds, locationIds)

    println("Books have been added to the specified locations.")
}

/**
 * Using similar styles to previous adding/ listing methods.
 * Asks the user to type the ID of the location they want to search (After adding the location(s).
 * Calls the findBooksInLocation() method in the BookLocationController to get the book IDs.
 * If the list of books isn't empty, it shows the book IDs in that location.
 * If the list of books are empty, it tells the user that no books are in the current location.
 * following the same pattern as the 'addBook' method
 */

fun findBooksInLocation() {

    println("Enter the IDs of the location you want to search: ")
    val locationId = readNextLine(" > ").toInt()


    val bookIds = bookLocationController.findBooksInLocation(locationId)

    if (bookIds.isNotEmpty()) {
        println("Books found in location $locationId: ${bookIds.joinToString(" , ")}")
    } else {
        println("No books found in location $locationId")
    }
}

fun save() {
    try {
        bookAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        bookAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun saveLocations() {
    try {
        locationAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun loadLocations() {
    try {
        locationAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun saveBookLocations() {
    try {
        bookLocationController.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun loadBookLocations() {
    try {
       bookLocationController.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}