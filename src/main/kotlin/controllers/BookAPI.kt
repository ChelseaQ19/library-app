package ie.setu.controllers

import ie.setu.models.Book

class BookAPI {

    private var books = ArrayList<Book>()

    fun add(book: Book): Boolean { //adding a book to our application
        return books.add(book)
    }

    fun listAllBooks(): String { //lists all books that are stored in the books list.
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
}