package moe.feng.nhentai.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Picture {

	@Expose @SerializedName("h") var height: Int = 0
	@Expose @SerializedName("w") var width: Int = 0
	private @Expose @SerializedName("t") var type: String = "j"
	val isJpg: Boolean get() = type == "j"
	val isPng: Boolean get() = type == "p"
	val fileType: String get() = if (isJpg) "jpg" else "png"

	fun setIsJpg() {
		type = "j"
	}

	fun setIsPng() {
		type = "p"
	}

}