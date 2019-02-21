package moe.feng.nhentai.dao

import moe.feng.nhentai.model.Book
import moe.feng.nhentai.util.extension.HawkInterface

object LatestBook: HawkInterface {

	override val STORE_NAME: String = LatestBook::class.java.toString()

	var list by nullableProperty<List<Book>>()
	var nowPage by property(defValue = 1)

}