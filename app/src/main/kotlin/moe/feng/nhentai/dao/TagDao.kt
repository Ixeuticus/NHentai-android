package moe.feng.nhentai.dao

import android.arch.persistence.room.*
import moe.feng.nhentai.model.Tag

/**
 * Tags cache database
 */
@Dao interface TagDao {

	/**
	 * Get all tags from cache
	 * @return All cached tags list
	 */
	@Query("SELECT * from ${Tag.TAG}")
	fun getAllTags(): List<Tag>

	/**
	 * Get favourite tags
	 * @return Favourite tags list
	 */
	@Query("SELECT * from ${Tag.TAG} WHERE isFavourite = 1")
	fun getFavouriteTags(): List<Tag>

	/**
	 * Get tag by id
	 * @return Specific id cached tag
	 */
	@Query("SELECT * from ${Tag.TAG} WHERE id = :tagId")
	fun getTag(tagId: Int): Tag

	/**
	 * Insert tag(s) to cache
	 * @param tags Tags being inserted
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg tags: Tag)

	/**
	 * Delete tag from cache
	 * @param tag Tag being deleted
	 */
	@Delete
	fun delete(tag: Tag)

}