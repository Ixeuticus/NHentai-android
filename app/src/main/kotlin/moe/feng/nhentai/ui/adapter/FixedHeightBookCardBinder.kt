package moe.feng.nhentai.ui.adapter

import moe.feng.nhentai.R
import moe.feng.nhentai.databinding.ItemBookCardFixedHeightBinding
import moe.feng.nhentai.model.Book
import moe.feng.nhentai.ui.common.NHBindingItemViewBinder

class FixedHeightBookCardBinder
	: NHBindingItemViewBinder<Book, ItemBookCardFixedHeightBinding>(R.layout.item_book_card_fixed_height)