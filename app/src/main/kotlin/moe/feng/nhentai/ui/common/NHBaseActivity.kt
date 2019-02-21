package moe.feng.nhentai.ui.common

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import moe.feng.nhentai.R
import moe.feng.nhentai.dao.AppDatabase
import moe.feng.nhentai.util.extension.*
import moe.shizuku.fontprovider.FontProviderClient
import org.jetbrains.anko.AnkoLogger

abstract class NHBaseActivity: AppCompatActivity(), AnkoLogger {

	internal val database by lazy { AppDatabase.INSTANCE ?: AppDatabase.init(this) }
	internal val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

	override fun onCreate(savedInstanceState: Bundle?) {
		if (!isFontInitialized && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			FontProviderClient.create(this)?.let { client ->
				client.setNextRequestReplaceFallbackFonts(true)
				client.replace("Noto Sans CJK", "sans-serif", "sans-serif-medium")
				isFontInitialized = true
			}
		}
		super.onCreate(savedInstanceState)
	}

	override fun setContentView(layoutResID: Int) {
		super.setContentView(layoutResID)
		this::setSupportActionBar.tryCall(toolbar)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (item?.itemId == android.R.id.home) {
			onBackPressed()
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {

		private var isFontInitialized = false

	}

}