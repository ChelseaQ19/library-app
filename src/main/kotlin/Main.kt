package ie.setu


import io.github.oshai.kotlinlogging.KotlinLogging
import java.lang.System.exit
import ie.setu.utils.readIntNotNull
import ie.setu.utils.readNextInt

private val logger = KotlinLogging.logger {}

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
            5  -> addnovel()
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
    logger.info { "addBook() function invoked" }
}

fun listBooks(){
    logger.info { "listBooks() function invoked" }
}

fun updateBook(){
    logger.info { "updateBook() function invoked" }
}

fun deleteBook(){
    logger.info { "deleteBook() function invoked" }
}

fun addnovel(){
    logger.info { "addNovel() function invoked" }
}

fun listNovels(){
    logger.info { "listNovels() function invoked" }
}

fun updateNovel(){
    logger.info { "updateNovel() function invoked" }
}

fun deleteNovel(){
    logger.info { "deleteNovel() function invoked" }
}

fun addLocation(){
    logger.info { "addLocation() function invoked"}
}

fun listLocations(){
    logger.info {"listLocations() function invoked"}
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}