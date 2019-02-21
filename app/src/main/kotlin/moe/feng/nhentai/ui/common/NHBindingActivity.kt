package moe.feng.nhentai.ui.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle

abstract class NHBindingActivity<T: ViewDataBinding>: NHBaseActivity() {

	abstract val LAYOUT_RES_ID: Int

	internal lateinit var binding: T

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, LAYOUT_RES_ID)
		onViewCreated(savedInstanceState)
	}

	abstract fun onViewCreated(savedInstanceState: Bundle?)

}