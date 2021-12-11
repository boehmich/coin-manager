package com.example.coinmanager.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.R
import com.example.coinmanager.adapter.WatchlistAdapter
import com.example.coinmanager.coin.CoinViewModel

class WatchlistFragment : Fragment(R.layout.watchlist_fragment) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: WatchlistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewWatchlist)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshWatchlist)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.createCoinsWatchlist()
        }

        viewModel.readCoinWatchlist().observe(viewLifecycleOwner){
            refreshCoinsInWatchlist(ArrayList(it))
            swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun refreshCoinsInWatchlist(coinsWatchlist: ArrayList<CoinWithUpdate>) {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = WatchlistAdapter(coinsWatchlist)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }

}