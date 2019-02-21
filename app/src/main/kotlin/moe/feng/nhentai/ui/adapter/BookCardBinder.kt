package moe.feng.nhentai.ui.adapter

import moe.feng.nhentai.R
import moe.feng.nhentai.databinding.ListItemBookCardBinding
import moe.feng.nhentai.model.Book
import moe.feng.nhentai.ui.common.NHBindingItemViewBinder

class BookCardBinder
	: NHBindingItemViewBinder<Book, ListItemBookCardBinding>(R.layout.list_item_book_card)