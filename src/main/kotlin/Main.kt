package ie.setu

import ie.setu.controllers.BookAPI
import ie.setu.controllers.LocationAPI
import ie.setu.controllers.NovelAPI
import ie.setu.controllers.BookLocationController
import ie.setu.models.Book
import ie.setu.models.Location
import ie.setu.models.Novel
import ie.setu.models.BookLocation
import ie.setu.utils.readNextDouble
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextLine
import io.github.oshai.kotlinlogging.KotlinLogging
import java.lang.System.exit

private val logger = KotlinLogging.logger {}
private val bookAPI = BookAPI()
private val novelAPI = NovelAPI()
private val locationAPI = LocationAPI()
private val bookLocationController = BookLocationController()



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
    > |   5) Add a novel               |
    > |   6) List all novels           |
    > |   7) Update a novel            |
    > |   8) Delete a novel            |
    > |   9) Add location              |
    > |   10) List all locations       |
    > |   11) Add books to locations   |
    > |   12) List location with books |
    > |   13) Find locations           |
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
            5  -> addNovel()
            6  -> listNovels()
            7  -> updateNovel()
            8  -> deleteNovel()
            9  -> addLocation()
            10 -> listLocations()
            11 -> addManyBooksToManyLocations()
            //12 -> listLocation
            //13 -> findLocation
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
    //logger.info { "deleteNotes() function invoked" }
    listBooks()
    if (bookAPI.numberOfBooks() > 0) {
        //only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the book to delete: ")
        //pass the index of the note to NoteAPI for deleting and check for success.
        val bookToDelete = bookAPI.deleteBook(indexToDelete)
        if (bookToDelete != null) {
            println("Delete Successful! Deleted book: ${bookToDelete.bookTitle}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun addNovel(){
    val novelTitle = readNextLine("Enter the title of the novel: ")
    val novelAuthor = readNextLine("Enter the author of the novel: ")
    val novelGenre = readNextLine("Enter the genre of the novel:")
    val novelPages = readNextInt("Enter the number of pages for the novel:")
    val novelPrice = readNextDouble("Enter the price of the novel:")
    val novelLanguage = readNextLine("Enter the language of the novel:")
    val isAdded = novelAPI.add(Novel(novelTitle, novelAuthor, novelGenre, novelPages, novelPrice, novelLanguage, false))

    if (isAdded) {
        println("Added Successfully to library")
    } else {
        println("Add Failed to library")
    }
}

fun listNovels(){
    println(novelAPI.listAllNovels())
}

fun updateNovel(){
    logger.info { "updateNovel() function invoked" }
}

fun deleteNovel(){
    logger.info { "deleteNovel() function invoked" }
}

fun addLocation(){
    val locationId = readNextInt("Enter the ID of the book location: ")
    val locationAisle = readNextInt("Enter the aisle number of the book location: ")
    val locationShelf = readNextLine("Enter the shelf number of the book location:")
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


fun exitApp(){
    println("Exiting...bye")
    exit(0)
}