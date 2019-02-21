package moe.feng.nhentai.dao

import android.arch.persistence.room.*
import moe.feng.nhentai.model.Book

/**
 * Books cache database
 */
@Dao interface BookDao {

	/**
	 * Get all books from cache
	 * @return All cached books list
	 */
	@Query("SELECT * from ${Book.TAG}")
	fun getAllBooks(): List<Book>

	/**
	 * Get favourite books
	 * @return Favourite books list
	 */
	@Query("SELECT * from ${Book.TAG} WHERE isFavourite = 1")
	fun getFavouriteBooks(): List<Book>

	/**
	 * Get book by id
	 * @return Specific id cached book
	 */
	@Query("SELECT * from ${Book.TAG} WHERE bookId = :bookId")
	fun getBook(bookId: Int): Book

	/**
	 * Insert book(s) to cache
	 * @param books Books being inserted
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg books: Book)

	/**
	 * Delete book from cache
	 * @param books Book being deleted
	 */
	@Delete
	fun delete(book: Book)

}