package ie.setu.controllers

import ie.setu.models.Book
import ie.setu.persistance.Serializer

class BookAPI(serializerType: Serializer) {

    private var books = ArrayList<Book>()
    private val serializer: Serializer = serializerType



    fun add(book: Book): Boolean {
        return books.add(book)
    }

    fun listAllBooks(): String {
        return if (books.isEmpty()) {
            "No books stored"
        } else {
            var listOfBooks = ""
            for (i in books.indices) {
                listOfBooks += "${i}: ${books[i]} \n"
            }
            listOfBooks
        }
    }

    fun listActiveBooks(): String {
        return if (numberOfActiveBooks() == 0) {
            "No active books stored"
        } else {
            var listOfActiveBooks = ""
            for (book in books) {
                if (!book.isBookArchived) {
                    listOfActiveBooks += "${books.indexOf(book)}: $book \n"
                }
            }
            listOfActiveBooks
        }
    }

    fun listArchivedBooks(): String {
        return if (numberOfArchivedBooks() == 0) {
            "No archived books stored"
        } else {
            var listOfArchivedBooks = ""
            for (book in books) {
                if (book.isBookArchived) {
                    listOfArchivedBooks += "${books.indexOf(book)}: $book \n"
                }
            }
            listOfArchivedBooks
        }
    }

    fun numberOfArchivedBooks(): Int {
        var counter = 0
        for (book in books) {
            if (book.isBookArchived) {
                counter++
            }
        }
        return counter
    }

    /**
     * Lambdas replaces the loop and the manual counter, so the code is more simple.
     * Processes only the books that match the genre directly.
     * Similar coding to the notes counter, adding the genre property from [BookAPI].
     */
    fun countBooksByGenre(genre: String): Int {
        var counter = 0
        return books.stream()
            .filter { book -> book.bookGenre == genre }
            .count()
            .toInt()
    }

    /**
     * Lambdas replaces the loop and the manual search, so the code is more simple.
     * Processes only the books that match the title directly.
     * Similar coding to the notes counter, adding the title property from [BookAPI].
     */

    fun searchByTitle(title: String): String {
        return books
            .filter { book -> book.bookTitle.contains(title, ignoreCase = true) }
            .joinToString(separator = "\n") {
                val index = books.indexOf(it)
                "$index: ${it.bookTitle} by ${it.bookAuthor}"
            }
    }

    fun numberOfActiveBooks(): Int {
        var counter = 0
        for (book in books) {
            if (!book.isBookArchived) {
                counter++
            }
        }
        return counter
    }

    fun numberOfBooks(): Int {
        return books.size
    }

    fun findBook(index: Int): Book? {
        return if (isValidListIndex(index, books)) {
            books[index]
        } else null
    }

    fun deleteBook(indexToDelete: Int): Book? {
        return if (isValidListIndex(indexToDelete, books)) {
            books.removeAt(indexToDelete)
        } else null
    }

    //utility method to determine if an index is valid in a list.
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    @Throws(Exception::class)
    fun load() {
        books = serializer.read() as ArrayList<Book>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(books)
    }
}



