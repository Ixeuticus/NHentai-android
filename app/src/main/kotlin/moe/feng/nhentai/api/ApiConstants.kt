package moe.feng.nhentai.api

import moe.feng.nhentai.BuildConfig
import moe.feng.nhentai.model.Tag

object ApiConstants {

	const val NHENTAI_HOME = "https://nhentai.net"
	const val NHENTAI_I = "https://i.nhentai.net"
	const val NHENTAI_T = "https://t.nhentai.net"

	const val NHBOOKS_UA = "NHBooks ${BuildConfig.VERSION_NAME}/Android " +
			"Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.0.0 Mobile"

	// Data urls
	fun getSearchUrl(content: String, pageNum: Int): String =
			"$NHENTAI_HOME/api/galleries/search?" +
					"query=${content.replace(" ", "+")}&" +
					"page=$pageNum"

	fun getBookDetailsUrl(bookId: String): String =
			"$NHENTAI_HOME/api/gallery/$bookId"

	fun getBookRecommendUrl(bookId: String): String =
			"$NHENTAI_HOME/api/gallery/$bookId/related"

	fun getGalleryUrl(galleryId: String): String =
			"$NHENTAI_I/galleries/$galleryId"

	fun getThumbGalleryUrl(galleryId: String): String =
			"$NHENTAI_T/galleries/$galleryId"

	fun getTagUrl(tag: Tag, isPopularList: Boolean, pageNum: Int): String =
			"$NHENTAI_HOME/api/galleries/tagged?" +
					"tag_id=${tag.id}" +
					"&page=$pageNum" +
					if (isPopularList) "&sort=popular" else ""

	fun getHomePageUrl(pageNum: Int): String =
			"$NHENTAI_HOME/api/galleries/all?page=$pageNum"

	// Picture urls
	fun getPictureUrl(galleryId: String, pageNum: String, fileType: String): String =
			"${getGalleryUrl(galleryId)}/$pageNum.$fileType"

	fun getThumbPictureUrl(galleryId: String, pageNum: String, fileType: String): String =
			"${getThumbGalleryUrl(galleryId)}/${pageNum}t.$fileType"

	fun getBigCoverUrl(galleryId: String): String =
			"${getThumbGalleryUrl(galleryId)}/cover.jpg"

	fun getOriginPictureUrl(galleryId: String, pageNum: String): String =
			getPictureUrl(galleryId, pageNum, "jpg")

	fun getBookThumbUrl(galleryId: String, fileType: String? = "jpg"): String =
			"${getThumbGalleryUrl(galleryId)}/thumb.${fileType ?: "jpg"}"

}