package moe.feng.nhentai.util.extension

import android.widget.ImageView
import com.github.florent37.materialimageloading.MaterialImageLoading
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat

private const val MATERIAL_ANIMATION_DURATION = 800

fun ImageView.animateMaterial()
		= MaterialImageLoading.animate(this).setDuration(MATERIAL_ANIMATION_DURATION).start()

fun Drawable.toBitmap(): Bitmap {
	val drawable = DrawableCompat.wrap(this).mutate()
	val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
			drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
	val canvas = Canvas(bitmap)
	drawable.setBounds(0, 0, canvas.width, canvas.height)
	drawable.draw(canvas)
	return bitmap
}