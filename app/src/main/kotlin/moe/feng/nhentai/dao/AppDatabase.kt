package moe.feng.nhentai.dao

import android.annotation.SuppressLint
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import moe.feng.nhentai.model.Book
import moe.feng.nhentai.model.History
import moe.feng.nhentai.model.Tag

@Database(
		entities = arrayOf(Book::class, Tag::class, History::class),
		version = 1,
		exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {

	abstract fun bookDao(): BookDao

	abstract fun tagDao(): TagDao

	abstract fun historyDao(): HistoryDao

	companion object {

		private val TAG = AppDatabase::class.java.simpleName

		@SuppressLint("StaticFieldLeak")
		var INSTANCE: AppDatabase? = null

		@Synchronized fun init(context: Context): AppDatabase {
			if (INSTANCE == null) {
				INSTANCE = Room.databaseBuilder(
						context.applicationContext,
						AppDatabase::class.java,
						TAG
				).build()
			}
			return INSTANCE!!
		}

	}

}