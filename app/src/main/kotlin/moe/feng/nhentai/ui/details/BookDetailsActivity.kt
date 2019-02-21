package moe.feng.nhentai.ui.details

import android.app.ActivityManager
import android.app.ActivityOptions
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.Menu
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import me.drakeet.multitype.MultiTypeAdapter
import moe.feng.nhentai.R
import moe.feng.nhentai.databinding.ActivityNewBookDetailsBinding
import moe.feng.nhentai.model.Book
import moe.feng.nhentai.ui.adapter.FixedHeightBookCardBinder
import moe.feng.nhentai.ui.adapter.TagBinder
import moe.feng.nhentai.ui.common.NHBindingActivity
import moe.feng.nhentai.ui.widget.SwipeBackCoordinatorLayout
import moe.feng.nhentai.util.extension.jsonAsObject
import moe.feng.nhentai.util.extension.objectAsJson
import moe.feng.nhentai.util.extension.registerOne
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.FrameLayout
import moe.feng.nhentai.ui.common.observers

class BookDetailsActivity: NHBindingActivity<ActivityNewBookDetailsBinding>(),
		SwipeBackCoordinatorLayout.OnSwipeListener {

	override val LAYOUT_RES_ID: Int = R.layout.activity_new_book_details

	private lateinit var viewModel: BookDetailsViewModel

	private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

	override fun onViewCreated(savedInstanceState: Bundle?) {
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ActivityOptions.makeTaskLaunchBehind()
		}

        viewModel = ViewModelProviders.of(this)[BookDetailsViewModel::class.java]

		binding.vm = viewModel
		binding.init()

		viewModel.data.observers += { _, _ ->
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				setTaskDescription(ActivityManager.TaskDescription(
						viewModel.data.get().prettyTitle ?: viewModel.data.get().jpTitle
				))
			}
		}
		viewModel.data.set(intent.getStringExtra(EXTRA_BOOK_JSON).jsonAsObject())

		viewModel.loadBookDataIfNecessary()
	}

	private fun ActivityNewBookDetailsBinding.init() {
		swipeBackLayout.setOnSwipeListener(this@BookDetailsActivity)

		fab.setOnClickListener {
			// TODO onClick
		}

		tagsList.layoutManager = ChipsLayoutManager.newBuilder(this@BookDetailsActivity)
				.setChildGravity(Gravity.START)
				.setScrollingEnabled(false)
				.build()
		tagsList.isNestedScrollingEnabled = false
		tagsList.adapter = MultiTypeAdapter().apply { registerOne(TagBinder()) }

		relatedList.layoutManager = LinearLayoutManager(this@BookDetailsActivity,
				LinearLayoutManager.HORIZONTAL, false)
		relatedList.adapter = MultiTypeAdapter().apply { registerOne(FixedHeightBookCardBinder()) }

		bottomSheetBehavior = BottomSheetBehavior.from(previewListLayout)
		bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
		bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
			override fun onSlide(bottomSheet: View, slideOffset: Float) {}
			override fun onStateChanged(bottomSheet: View, newState: Int) {
				if (newState != BottomSheetBehavior.STATE_HIDDEN) {
					hideFab()
					previewListBackground.animate().alpha(1f).start()
				} else {
					showFab()
					previewListBackground.animate().alpha(0f).start()
				}
			}
		})
		previewListBackground.setOnTouchListener { _, _ ->
			val state = bottomSheetBehavior.state
			if (state == BottomSheetBehavior.STATE_COLLAPSED
					|| state == BottomSheetBehavior.STATE_EXPANDED) {
				bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
				true
			} else false
		}

		previewList.layoutManager = GridLayoutManager(this@BookDetailsActivity,
				3, GridLayoutManager.VERTICAL, false)
		previewList.adapter = MultiTypeAdapter().apply { registerOne(PreviewItemBinder()) }

		previewListButton.setOnClickListener {
			bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED }
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_details, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onBackPressed() {
		if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
			bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
			return
		}
		super.onBackPressed()
	}

	override fun canSwipeBack(dir: Int): Boolean {
		return (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN)
				&& dir == SwipeBackCoordinatorLayout.UP_DIR
				|| (dir == SwipeBackCoordinatorLayout.DOWN_DIR && binding.appBarLayout.y >= 0)
	}

	override fun onSwipeProcess(percent: Float) {
		binding.root.setBackgroundColor(SwipeBackCoordinatorLayout.getBackgroundColor(percent))
		if (percent >= 0.1F) {
			hideFab()
		} else {
			showFab()
		}
	}

	private fun hideFab() {
		val params = binding.fab.layoutParams as CoordinatorLayout.LayoutParams
		val behavior = params.behavior as FloatingActionButton.Behavior?

		if (behavior != null) {
			behavior.isAutoHideEnabled = false
		}

		binding.fab.hide()
	}

	private fun showFab() {
		binding.fab.show()
		val params = binding.fab.layoutParams as CoordinatorLayout.LayoutParams
		val behavior = params.behavior as FloatingActionButton.Behavior?

		if (behavior != null) {
			behavior.isAutoHideEnabled = true
		}
	}

	override fun onSwipeFinish(dir: Int) {
		finish()
	}

	companion object {

		const val EXTRA_BOOK_JSON = "book_json"

		@JvmStatic fun launch(context: Context, book: Book) {
			Intent(context, BookDetailsActivity::class.java).apply {
				flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
				} else {
					Intent.FLAG_ACTIVITY_NEW_TASK
				}
				putExtra(EXTRA_BOOK_JSON, book.objectAsJson())
			}.run(context::startActivity)
		}

	}

}