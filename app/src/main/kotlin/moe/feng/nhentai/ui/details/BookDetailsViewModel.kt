package moe.feng.nhentai.ui.details

import android.databinding.ObservableField
import moe.feng.nhentai.api.BookApi
import moe.feng.nhentai.api.PageApi
import moe.feng.nhentai.model.Book
import moe.feng.nhentai.ui.common.NHViewModel

class BookDetailsViewModel: NHViewModel() {

	val data: ObservableField<Book> = ObservableField()
	val relatedBooks: ObservableField<List<Book>?> = ObservableField()

	fun loadBookDataIfNecessary() = ui {
		if (data.get().pageCount <= 0) {
			val bookResult = BookApi.getBookAsync(data.get().bookId).await()
			data.set(bookResult)
		}
		loadRelatedBooks()
	}

	private fun loadRelatedBooks() = ui {
		val pageResult = PageApi.getPageListAsync(data.get().relatedApiUrl).await()
		pageResult?.result?.let(relatedBooks::set)
	}


}