package moe.feng.nhentai.api

import kotlinx.coroutines.experimental.*
import moe.feng.nhentai.model.Book
import moe.feng.nhentai.util.HttpUtils
import moe.feng.nhentai.util.extension.readAsJsonObject

object BookApi {

	suspend fun getBook(bookId: String): Book? = ApiConstants.getBookDetailsUrl(bookId)
			.run(HttpUtils::requestUrl)
			?.readAsJsonObject()

	fun getBookAsync(bookId: String): Deferred<Book?> =
			async(CommonPool) { getBook(bookId) }

}