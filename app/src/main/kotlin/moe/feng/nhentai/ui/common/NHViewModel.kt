package moe.feng.nhentai.ui.common

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.ObservableField
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger

open class NHViewModel: ViewModel(), AnkoLogger {

	internal fun <T> ui(block: suspend CoroutineScope.() -> T) {
		async(UI) {
			try {
				block()
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}
	}

}

val <T> ObservableField<T>.observers get() = ObserverArray(this)

class ObserverArray<T> internal constructor(private val field: ObservableField<T>) {

	operator fun plusAssign(observer: Observable.OnPropertyChangedCallback) {
		field.addOnPropertyChangedCallback(observer)
	}

	operator fun plusAssign(observer: (Observable?, Int) -> Unit) {
		field.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
			override fun onPropertyChanged(p0: Observable?, p1: Int) { observer(p0, p1) }
		})
	}

	operator fun minusAssign(observer: Observable.OnPropertyChangedCallback) {
		field.removeOnPropertyChangedCallback(observer)
	}

}