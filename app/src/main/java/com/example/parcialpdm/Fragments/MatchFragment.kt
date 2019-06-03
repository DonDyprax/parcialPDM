package com.example.parcialpdm.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialpdm.Database.Entities.Match
import com.example.parcialpdm.Adapters.MatchAdapter
import com.example.parcialpdm.R
import kotlinx.android.synthetic.main.match_list.view.*
import java.lang.ClassCastException

class MatchFragment : Fragment() {
    private var listenerTool: clickListener? = null
    private lateinit var matchAdapter: MatchAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
                MatchFragment().apply {

                }
    }

    interface clickListener {
        fun itemClick(match: Match)
        fun delete(match: Match)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.match_list, container, false)
        initRecyclerView(view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is clickListener) {
            listenerTool = context
        } else {
            throw ClassCastException("Se necesita una implementacion de  la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    fun updateAdapter(matches: List<Match>) {
        this.matchAdapter.setMatches(matches)
    }

    private fun initRecyclerView(container: View) {

        matchAdapter =
                MatchAdapter({ match: Match -> listenerTool?.itemClick(match)}, {match: Match -> listenerTool?.delete(match)})

        container.rv_match_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = matchAdapter
        }
    }
}