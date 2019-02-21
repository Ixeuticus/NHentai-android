package moe.feng.nhentai.util.extension

import com.orhanobut.hawk.Hawk
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface HawkInterface {

	val STORE_NAME: String

	fun <T> property(keyName: String? = null, defValue: T? = null)
			= HawkProperty(keyName, defValue)

	fun <T> nullableProperty(keyName: String? = null, defValue: T? = null)
			= NullableHawkProperty(keyName, defValue)

}

class HawkProperty<T> internal constructor(private val keyName: String? = null,
                                           val defValue: T? = null)
	: ReadWriteProperty<HawkInterface, T> {

	override fun getValue(thisRef: HawkInterface, property: KProperty<*>): T {
		return if (defValue != null) {
			Hawk.get("${thisRef.STORE_NAME}$${keyName ?: property.name}", defValue)
		} else {
			Hawk.get("${thisRef.STORE_NAME}$${keyName ?: property.name}")
		}
	}
	override fun setValue(thisRef: HawkInterface, property: KProperty<*>, value: T) {
		Hawk.put("${thisRef.STORE_NAME}$${keyName ?: property.name}" , value)
	}
}

class NullableHawkProperty<T> internal constructor(private val keyName: String? = null,
                                                   val defValue: T? = null)
	: ReadWriteProperty<HawkInterface, T?> {
	override fun getValue(thisRef: HawkInterface, property: KProperty<*>): T? {
		return if (defValue != null) {
			Hawk.get("${thisRef.STORE_NAME}$${keyName ?: property.name}", defValue)
		} else {
			Hawk.get("${thisRef.STORE_NAME}$${keyName ?: property.name}")
		}
	}
	override fun setValue(thisRef: HawkInterface, property: KProperty<*>, value: T?) {
		Hawk.put("${thisRef.STORE_NAME}$${keyName ?: property.name}", value)
	}
}