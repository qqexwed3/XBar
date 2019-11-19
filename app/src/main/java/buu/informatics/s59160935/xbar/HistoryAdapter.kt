package buu.informatics.s59160935.xbar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160935.xbar.database.Score
import buu.informatics.s59160935.xbar.databinding.ScoreRowFragmentBinding
import java.util.*

class HistoryAdapter() : ListAdapter<Score,
        HistoryAdapter.ScoreViewHolder>(DiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val binding =
            ScoreRowFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score = getItem(position)
        holder.bind(score)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Score>() {
        override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem.scoreId == newItem.scoreId
        }
    }

    class ScoreViewHolder(private var binding:
                          ScoreRowFragmentBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(score: Score) {
            binding.scoreData = score
            binding.dateString = Date(score.date).toLocaleString()
            binding.executePendingBindings()
        }
    }
}