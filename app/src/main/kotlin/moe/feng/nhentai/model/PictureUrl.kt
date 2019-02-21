package moe.feng.nhentai.model

import com.google.gson.annotations.Expose

class PictureUrl(
		@Expose var url: String,
		var from: Book? = null,
		@Expose var title: String = ""
)