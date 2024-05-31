package com.example.githubclient.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemUserBinding
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.IImageLoader
import com.example.githubclient.mvp.view.list.UserItemView

class UsersRVAdapter(
    private val presenter: IUserListPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(viewBinding.root), UserItemView {
        /** Вынести в отдельный файл с константами (const val INVALID_INDEX = -1) **/
        override var pos = -1

        override fun setLogin(text: String) = with(viewBinding) {
            Log.d("UsersRVAdapter", "Setting login: $text at position $pos")
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) {
            Log.d("UsersRVAdapter", "Loading avatar: $url at position $pos")
            imageLoader.loadInfo(url, viewBinding.ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount(): Int {
        val count = presenter.getCount()
        Log.d("UsersRVAdapter", "Item count: $count")
        return count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("UsersRVAdapter", "Binding view at position $position")
        presenter.bindView(holder.apply {
            pos = position
        })
    }
}