package dev.dpastukhov.coinmarketinfo.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.dpastukhov.coinmarketinfo.R
import dev.dpastukhov.coinmarketinfo.databinding.CoinItemBinding
import dev.dpastukhov.coinmarketinfo.domain.Coin

object UserComparator : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        // Id is unique.
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}

class CoinAdapter : PagingDataAdapter<Coin, CoinAdapter.CoinViewHolder>(UserComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }

    class CoinViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CoinItemBinding.bind(itemView)

        fun bind(model: Coin?) {
            binding.name.text = model?.name
            binding.price.text = "${model?.price} $"
            binding.percentChange1h.text = "${model?.percentChange1h} %"
        }
    }
}

