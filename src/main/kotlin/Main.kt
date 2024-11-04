package ie.setu

fun mainMenu() : Int {
    println("")
    println("--------------------")
    println("LIBRARY MANAGEMENT APP")
    println("--------------------")
    println("LIBRARY MENU")
    println("  1) Add a book")
    println("  2) List all books")
    println("  3) Update a book")
    println("  4) Delete a book")
    println("  5) Add a Novel")
    println("  6) List all Novels ")
    println("  7) Update a novel")
    println("  8) Delete a novel")
    println("  9) Add a location")
    println("  10) List all locations")
    println("--------------------")
    println("  0) Exit")
    println("--------------------")
    print("==>> ")
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
            5  -> addNovel()
            6  -> listNovels()
            7  -> updateNovel()
            8  -> deleteNovel()
            9  -> addLocation()
            10 -> listLocations()
            0  -> exitApp()
            else -> println("Invalid option entered: " + option)
        }
    } while (true)
}
//creating corresponding calls for functions above
fun addBook(){
    println("You chose Add Note")
}

fun listBooks(){
    println("You chose List Notes")
}

fun updateBook(){
    println("You chose Update Note")
}

fun deleteBook(){
    println("You chose Delete Note")
}

fun addnovel(){
    println("You chose Add Note")
}

fun listNovels(){
    println("You chose List Notes")
}

fun updateNovel(){
    println("You chose Update Note")
}

fun deleteNovel(){
    println("You chose Delete Note")
}

fun addLocation(){
    println("You chose Update Note")
}

fun listLocations(){
    println("You chose Delete Note")
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}