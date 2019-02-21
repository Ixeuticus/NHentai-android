package moe.feng.nhentai.ui.category

import android.databinding.ObservableField
import moe.feng.nhentai.model.Tag
import moe.feng.nhentai.ui.common.NHViewModel

class CategoryViewModel: NHViewModel() {

	val tag: ObservableField<Tag> = ObservableField()

}