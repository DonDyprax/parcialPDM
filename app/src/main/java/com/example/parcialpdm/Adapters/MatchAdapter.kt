package com.example.parcialpdm.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialpdm.Database.Entities.Match
import com.example.parcialpdm.R
import kotlinx.android.synthetic.main.match_card_view.view.*

class MatchAdapter(
    private val clickListener: (Match) -> Unit,
    private val deleteMatch: (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    private var matches = emptyList<Match>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(matches[position], clickListener, deleteMatch)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Match, clickListener: (Match) -> Unit, deleteMatch: (Match) -> Unit) = with(itemView){
            home_team.text = item.homeTeam
            away_team.text = item.awayTeam
            match_date.text = item.date
            this.setOnClickListener { clickListener(item) }
        }
    }

    fun setMatches(matches: List<Match>){
        this.matches = matches
        notifyDataSetChanged()
    }


}