package ie.setu.models

data class Book(
    val bookId: Int,
    val bookAuthor: String,
    val bookISBN: String,
    val bookTitle: String,
    val bookPrice: Double,
    val bookPages: Int,
    val bookGenre: String,
    val bookLanguage: String,
    val isBookArchived:Boolean){
}