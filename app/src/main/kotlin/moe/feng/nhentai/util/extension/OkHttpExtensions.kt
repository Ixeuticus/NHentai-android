package moe.feng.nhentai.util.extension

import android.util.Log
import okhttp3.Response
import okhttp3.ResponseBody

private const val TAG = "OkHttpExtensions"

fun Response.readAsString(): String? = this.body()?.tryRun(ResponseBody::string)?.apply {
	Log.i(TAG, "Result: " + this)
}

inline fun <reified T> Response.readAsJsonObject(): T? = this.readAsString()?.jsonAsObject()