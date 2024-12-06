package controllers
import ie.setu.persistance.Serializer
import ie.setu.controllers.BookAPI
import ie.setu.models.Book
import ie.setu.persistance.XMLSerializer
import ie.setu.persistance.JSONSerializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Locale
import java.io.File

class BookAPITest {

    private var mysteryBook: Book? = null
    private var sciFiBook: Book? = null
    private var historyBook: Book? = null
    private var fantasyBook: Book? = null
    private var biographyBook: Book? = null
    private var populatedBooks: BookAPI? = BookAPI(XMLSerializer(File("books.xml")))
    private var emptyBooks: BookAPI? = BookAPI(XMLSerializer(File("empty-books.xml")))

    @BeforeEach
    fun setup() {
        mysteryBook =
            Book(1,"Agatha Christie", "1111111111", "Mystery of the Nile", 10.99, 250, "Mystery", "English", false )
        sciFiBook = Book(2,"Isaac Asimov", "2222222222", "The Stars, Like Dust", 15.50, 300, "Sci-Fi", "English", true )
        historyBook =
            Book(3,"Doris Kearns Goodwin", "3333333333", "Team of Rivals", 20.0, 900, "History", "English", false )
        fantasyBook = Book(4,"J.K. Rowling", "4444444444", "Harry Potter", 9.99, 400, "Fantasy", "English", false)
        biographyBook = Book(5,"Walter Isaacson", "5555555555", "Steve Jobs", 12.0, 600, "Biography", "English", true )

        //adding 5 Books to the books api
        populatedBooks!!.add(mysteryBook!!)
        populatedBooks!!.add(sciFiBook!!)
        populatedBooks!!.add(historyBook!!)
        populatedBooks!!.add(fantasyBook!!)
        populatedBooks!!.add(biographyBook!!)
    }

    @AfterEach
    fun tearDown() {
        mysteryBook = null
        sciFiBook = null
        historyBook = null
        fantasyBook = null
        biographyBook = null
        populatedBooks = null
        emptyBooks = null
    }

    @Nested
    inner class AddBooks {

        @Test
        fun `adding a Book to a populated list adds to ArrayList`() {
            val newBook = Book(6,"H.G. Wells", "6666666666", "The Invisible Man", 8.99, 280, "Sci-Fi", "English", false)
            assertTrue(populatedBooks!!.add(newBook))
        }

        @Test
        fun `adding a Book to an empty list adds to ArrayList`() {
            val newBook = Book(7,"George Orwell", "7777777777", "1984", 9.99, 300, "Dystopian", "English", false)
            assertTrue(emptyBooks!!.add(newBook))
        }
    }

    @Nested
    inner class ListBooks {

        @Test
        fun `listAllBooks returns No Books Stored message when ArrayList is empty`() {
            assertEquals(0, emptyBooks!!.numberOfBooks())
            assertTrue(emptyBooks!!.listAllBooks().lowercase().contains("no books"))
        }
    //make sure that test contains an element from the book, such as title.
        @Test
        fun `listAllBooks returns Books when ArrayList has books stored`() {
            assertEquals(5, populatedBooks!!.numberOfBooks())
            val booksString = populatedBooks!!.listAllBooks().lowercase()
            assertTrue(booksString.contains("mystery of the nile".lowercase()))
            assertTrue(booksString.contains("the stars, like dust".lowercase()))
            assertTrue(booksString.contains("team of rivals".lowercase()))
            assertTrue(booksString.contains("harry potter".lowercase()))
            assertTrue(booksString.contains("steve jobs".lowercase()))//adding lowercase so it becomes case-insensitive
        }
    }

    @Nested
    inner class ArchivedBooks {

        @Test
        fun `listActiveBooks returns no active books stored when ArrayList is empty`() {
            assertEquals(0, emptyBooks!!.numberOfActiveBooks())
            assertTrue(emptyBooks!!.listActiveBooks().lowercase().contains("no active books")
            )
        }

        @Test
        fun `listActiveBooks returns active books when ArrayList has active books stored`() {
            assertEquals(3, populatedBooks!!.numberOfActiveBooks())
            val activeBooksString = populatedBooks!!.listActiveBooks().lowercase()
            assertTrue(activeBooksString.contains("Mystery of the Nile".lowercase()))
            assertFalse(activeBooksString.contains("The Stars, Like Dust".lowercase()))
            assertTrue(activeBooksString.contains("Team of Rivals".lowercase()))
            assertTrue(activeBooksString.contains("Harry Potter".lowercase()))
            assertFalse(activeBooksString.contains("Steve Jobs".lowercase()))
        }

        @Test
        fun `listArchivedBooks returns no archived books when ArrayList is empty`() {
            assertEquals(0, emptyBooks!!.numberOfArchivedBooks())
            assertTrue(
                emptyBooks!!.listArchivedBooks().lowercase().contains("no archived books")
            )
        }

        @Test
        fun `listArchivedBooks returns archived books when ArrayList has archived books stored`() {
            assertEquals(2, populatedBooks!!.numberOfArchivedBooks())
            val archivedBooksString = populatedBooks!!.listArchivedBooks().lowercase(Locale.getDefault())
            assertFalse(archivedBooksString.contains("Mystery of the Nile".lowercase()))
            assertTrue(archivedBooksString.contains("The Stars, Like Dust".lowercase()))
            assertFalse(archivedBooksString.contains("Team of Rivals".lowercase()))
            assertFalse(archivedBooksString.contains("Harry Potter".lowercase()))
            assertTrue(archivedBooksString.contains("Steve Jobs".lowercase()))
        }
    }

    @Nested
    inner class PersistenceTests {

        @Test
        fun `saving and loading an empty collection in XML doesn't crash app`() {
            // Saving an empty books.XML file.
            val storingBooks =BookAPI(XMLSerializer(File("books.xml")))
            storingBooks.store()

            //Loading the empty books.xml file into a new object
            val loadedBooks = BookAPI(XMLSerializer(File("books.xml")))
            loadedBooks.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            assertEquals(0, storingBooks.numberOfBooks())
            assertEquals(0, loadedBooks.numberOfBooks())
            assertEquals(storingBooks.numberOfBooks(), loadedBooks.numberOfBooks())
        }

        @Test
        fun `saving and loading an loaded collection in XML doesn't loose data`() {
            // Storing 3 books to the books.XML file.
            val storingBooks = BookAPI(XMLSerializer(File("books.xml")))
            storingBooks.add(mysteryBook!!)
            storingBooks.add(sciFiBook!!)
            storingBooks.add(historyBook!!)
            storingBooks.store()

            //Loading notes.xml into a different collection
            val loadedBooks = BookAPI(XMLSerializer(File("books.xml")))
            loadedBooks.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            assertEquals(3, storingBooks.numberOfBooks())
            assertEquals(3, loadedBooks.numberOfBooks())
            assertEquals(storingBooks.numberOfBooks(), loadedBooks.numberOfBooks())
            assertEquals(storingBooks.findBook(0), loadedBooks.findBook(0))
            assertEquals(storingBooks.findBook(1), loadedBooks.findBook(1))
            assertEquals(storingBooks.findBook(2), loadedBooks.findBook(2))
        }
    }

    @Test
    fun `saving and loading an empty collection in JSON doesn't crash app`() {
        // Saving an empty books.json file.
        val storingBooks =BookAPI(JSONSerializer(File("books.json")))
        storingBooks.store()

        //Loading the empty books.json file into a new object
        val loadedBooks = BookAPI(JSONSerializer(File("books.json")))
        loadedBooks.load()

        //Comparing the source of the notes (storingBooks) with the JSON loaded notes (loadedBooks)
        assertEquals(0, storingBooks.numberOfBooks())
        assertEquals(0, loadedBooks.numberOfBooks())
        assertEquals(storingBooks.numberOfBooks(), loadedBooks.numberOfBooks())
    }

    @Test
    fun `saving and loading an loaded collection in JSON doesn't loose data`() {
        // Storing 3 books to the books.XML file.
        val storingBooks = BookAPI(JSONSerializer(File("books.json")))
        storingBooks.add(mysteryBook!!)
        storingBooks.add(sciFiBook!!)
        storingBooks.add(historyBook!!)
        storingBooks.store()

        //Loading notes.xml into a different collection
        val loadedBooks = BookAPI(JSONSerializer(File("books.json")))
        loadedBooks.load()

        //Comparing the source of the books (storingBooks) with the JSON loaded notes (loadedBooks)
        assertEquals(3, storingBooks.numberOfBooks())
        assertEquals(3, loadedBooks.numberOfBooks())
        assertEquals(storingBooks.numberOfBooks(), loadedBooks.numberOfBooks())
        assertEquals(storingBooks.findBook(0), loadedBooks.findBook(0))
        assertEquals(storingBooks.findBook(1), loadedBooks.findBook(1))
        assertEquals(storingBooks.findBook(2), loadedBooks.findBook(2))
    }
}