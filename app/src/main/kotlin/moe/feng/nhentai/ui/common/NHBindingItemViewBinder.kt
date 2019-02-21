package moe.feng.nhentai.ui.common

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.drakeet.multitype.ItemViewBinder
import moe.feng.nhentai.BR
import org.jetbrains.anko.AnkoLogger

abstract class NHBindingItemViewBinder<M, DB: ViewDataBinding>(private val layoutResId: Int):
		ItemViewBinder<M, NHBindingItemViewBinder.BindingHolder<M, DB>>(), AnkoLogger {

	@CallSuper
	override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
			: BindingHolder<M, DB> {
		val binding: DB = DataBindingUtil.inflate(inflater, layoutResId, parent, false)
		return BindingHolder(binding, this::onViewHolderCreated)
	}

	@CallSuper
	override fun onBindViewHolder(holder: BindingHolder<M, DB>, item: M) {
		holder.currentItem = item
		holder.binding.setVariable(BR.item, item)
		holder.binding.executePendingBindings()
	}

	open fun onViewHolderCreated(holder: BindingHolder<M, DB>) {

	}

	class BindingHolder<M, out T: ViewDataBinding>(
			val binding: T,
			initBlock: BindingHolder<M, T>.() -> Unit
	) : RecyclerView.ViewHolder(binding.root) {

		init { initBlock() }

		var currentItem: M? = null

		val context: Context = itemView.context

	}

}