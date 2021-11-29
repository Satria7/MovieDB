package com.satria.jpro.lastsubmission.ui.home.content.movie
//copyright satria junanda//
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.satria.jpro.lastsubmission.BuildConfig
import com.satria.jpro.lastsubmission.R
import com.satria.jpro.lastsubmission.data.source.local.entity.MovieEntity
import com.satria.jpro.lastsubmission.utils.Constants
import com.satria.jpro.lastsubmission.utils.loadFromUrl
import kotlinx.android.synthetic.main.item_row_data.view.*
//copyright satria junanda//
class FavoriteAdapter(private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, FavoriteAdapter.ListViewHolder>(DIFF_CALLBACK)  {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId

            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MovieEntity) {
            with(itemView) {
                data.poster?.let {
                    img_data.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W185 + it)
                }
                tv_data_title.text = data.name
                tv_data_desc.text = data.desc
                card_item.setOnClickListener {
                    callback.onItemClicked(data)
                }
                img_data.setOnClickListener {
                    callback.onItemClicked(data)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_data3, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val model = getItem(position)
        if (model != null) {
            holder.bind(model)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: MovieEntity): Unit {
            itemView.tv_data_title.text = model.name
        }
    }


}
//copyright satria junanda//