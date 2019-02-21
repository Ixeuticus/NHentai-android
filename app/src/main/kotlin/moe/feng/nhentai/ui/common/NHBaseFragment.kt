package moe.feng.nhentai.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger

abstract class NHBaseFragment: Fragment(), AnkoLogger {

	abstract val LAYOUT_RES_ID: Int

	override fun onCreateView(inflater: LayoutInflater,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		return inflater.inflate(LAYOUT_RES_ID, container, false)
	}

}