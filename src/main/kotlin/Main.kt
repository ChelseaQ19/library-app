package ie.setu

import ie.setu.controllers.BookAPI
import ie.setu.controllers.LocationAPI
import ie.setu.controllers.NovelAPI
import ie.setu.models.Book
import ie.setu.models.Novel
import ie.setu.models.Location
import io.github.oshai.kotlinlogging.KotlinLogging
import java.lang.System.exit
import ie.setu.utils.readIntNotNull
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextDouble
import ie.setu.utils.readNextLine

private val logger = KotlinLogging.logger {}
private val bookAPI = BookAPI()
private val novelAPI = NovelAPI()
private val locationAPI = LocationAPI()

fun main() {
    runMenu()
}

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
    > ----------------------------------
    > |   0) Exit                      |
    > ----------------------------------
    ==>> """.trimMargin(">"))
    return readNextInt(" > ==>>")
}
//creating menu for library management system
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
            0  -> exitApp()
            else -> println("Invalid option entered: ${option}")
        }
    } while (true)
}
//creating corresponding calls for functions above
fun addBook(){
    val bookAuthor = readNextLine("Enter the author of the book: ")
    val bookISBN = readNextLine("Enter the ISBN of the book: ")
    val bookTitle = readNextLine("Enter the title of the book:")
    val bookPrice = readNextDouble("Enter the price of the book:")
    val bookPages = readNextInt("Enter the number of pages for the book:")
    val bookGenre = readNextLine("Enter the genre of the book:")
    val bookLanguage = readNextLine("Enter the language of the book:")
    val isAdded = bookAPI.add(Book(bookAuthor, bookISBN, bookTitle, bookPrice, bookPages, bookGenre, bookLanguage, false))

    if (isAdded) {
        println("Added Successfully to library")
    } else {
        println("Add Failed to library")
    }
}

fun listBooks(){
    println(bookAPI.listAllBooks())
}

fun updateBook(){
    logger.info { "updateBook() function invoked" }
}

fun deleteBook(){
    logger.info { "deleteBook() function invoked" }
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
    val locationAisle = readNextInt("Enter the aisle number of the book location: ")
    val locationShelf = readNextInt("Enter the shelf number of the book location:")
    val locationIndex = readNextInt("Enter the index number of the book location:")
    val isAdded = locationAPI.add(Location(locationAisle, locationShelf, locationIndex, false))

    if (isAdded) {
        println("Added Successfully to library")
    } else {
        println("Add Failed to library")
    }
}


fun listLocations(){
    logger.info {"listLocations() function invoked"}
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}