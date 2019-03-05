package com.mvvm.dagger.rx.sample.photos

import com.mvvm.dagger.rx.sample.R
import com.mvvm.dagger.rx.sample.base.BaseRecyclerViewAdapter
import com.mvvm.dagger.rx.sample.data.room.Photo

class PhotosAdapter(photoList: List<Photo>?,listener: BaseRecyclerViewAdapter.OnItemClickListener) : BaseRecyclerViewAdapter(photoList,listener) {

    override fun getItemLayoutId(): Int = R.layout.item_photo

}