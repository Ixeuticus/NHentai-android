package moe.feng.nhentai.ui.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class NHBindingFragment<T: ViewDataBinding>: NHBaseFragment() {

	internal var binding: T? = null

	override fun onCreateView(inflater: LayoutInflater,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		return DataBindingUtil
				.inflate<T>(inflater, LAYOUT_RES_ID, container, false)
				.apply { binding = this }
				.root
	}

}