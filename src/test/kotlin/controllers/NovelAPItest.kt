package controllers

import ie.setu.controllers.NovelAPI
import ie.setu.models.Book
import ie.setu.models.Novel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NovelPITest {

    private var mysteryNovel: Novel? = null
    private var sciFiNovel: Novel? = null
    private var historyNovel: Novel? = null
    private var fantasyNovel: Novel? = null
    private var biographyNovel: Novel? = null
    private var populatedNovels: NovelAPI? = NovelAPI()
    private var emptyNovel: NovelAPI? = NovelAPI()

    @BeforeEach
    fun setup() {
        mysteryNovel = Novel("The Book Thief", "Markus Zusak", "Historical Fiction", 552, 12.99, "English", false)
        sciFiNovel = Novel("Life of Pi", "Yann Martel", "Adventure", 319, 15.50, "English", false)
        historyNovel = Novel("The Girl with the Dragon Tattoo", "Stieg Larsson", "Thriller", 465, 9.99, "English", true)
        fantasyNovel = Novel("The Hobbit", "J.R.R. Tolkien", "Fantasy", 310, 11.50, "English", false)
        biographyNovel = Novel("Pride and Prejudice", "Jane Austen", "Classic", 432, 8.99, "English", false)

        //adding 5 Note to the notes api
        populatedNovels!!.add(mysteryNovel!!)
        populatedNovels!!.add(sciFiNovel!!)
        populatedNovels!!.add(historyNovel!!)
        populatedNovels!!.add(fantasyNovel!!)
        populatedNovels!!.add(biographyNovel!!)
    }

    @AfterEach
    fun tearDown() {
        mysteryNovel = null
        sciFiNovel = null
        historyNovel = null
        fantasyNovel = null
        biographyNovel = null
        populatedNovels = null
        emptyNovel = null
    }

    @Nested
    inner class AddNovels {
        @Test
        fun `adding a Book to a populated list adds to ArrayList`() {
            val newNovel =
                Novel("The Catcher in the Rye", "J.D. Salinger", "Literary Fiction", 277, 14.99, "English", false)
            assertTrue(populatedNovels!!.add(newNovel))
        }

        @Test
        fun `adding a Book to an empty list adds to ArrayList`() {
            val newNovel = Novel("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 180, 10.00, "English", false)
            assertTrue(emptyNovel!!.add(newNovel))
        }
    }
}
