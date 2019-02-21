package moe.feng.nhentai.dao

import android.arch.persistence.room.*
import moe.feng.nhentai.model.History

/**
 * History database
 */
@Dao interface HistoryDao {

	/**
	 * Get all histories
	 * @return All histories list
	 */
	@Query("SELECT * from ${History.TAG}")
	fun getAllHistories(): List<History>

	/**
	 * Insert history(s)
	 * @param history Histories being inserted
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg history: History)

	/**
	 * Delete history from cache
	 * @param history History being deleted
	 */
	@Delete
	fun delete(history: History)

}