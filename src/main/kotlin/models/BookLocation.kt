package ie.setu.models


/**
 * Represents a many-to-many relationship between book and location.
 * Associates a specific book with a specific location in the library application.
 * @property bookId is the unique identifier for book.
 * @property locationId is the unique identifier for location.
 */
data class BookLocation(
    var bookId: Int,
    var locationId: Int
)