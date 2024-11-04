package ie.setu

import java.lang.System.exit

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
    return readlnOrNull()?.toIntOrNull() ?: -1
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
    println("You chose Add Book")
}

fun listBooks(){
    println("You chose List Books")
}

fun updateBook(){
    println("You chose Update Book")
}

fun deleteBook(){
    println("You chose Delete Book")
}

fun addnovel(){
    println("You chose Add Novel")
}

fun listNovels(){
    println("You chose List Novels")
}

fun updateNovel(){
    println("You chose Update Novel")
}

fun deleteNovel(){
    println("You chose Delete Novel")
}

fun addLocation(){
    println("You chose Add Location")
}

fun listLocations(){
    println("You chose List Locations")
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}