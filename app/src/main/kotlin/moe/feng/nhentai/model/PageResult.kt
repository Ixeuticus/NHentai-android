package moe.feng.nhentai.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageResult(
		@Expose var result: List<Book>?,
		@Expose @SerializedName("num_pages") var totalPages: Int,
		@Expose @SerializedName("per_page") var perPageCount: Int
)