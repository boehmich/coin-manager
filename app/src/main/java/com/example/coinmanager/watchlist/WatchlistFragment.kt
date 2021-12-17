package com.example.coinmanager.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.R
import com.example.coinmanager.adapter.WatchlistAdapter

class WatchlistFragment : Fragment(R.layout.watchlist_fragment) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: WatchlistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewWatchlist)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshWatchlist)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateCoinsWatchlist()
        }

        viewModel.readCoinWatchlist().observe(viewLifecycleOwner, {
            it.forEach {
                var percent = (it.coinWithUpdate.priceActual / it.coin.pricePurchased - 1) * 100
                it.coin.priceChangedPercent = Math.round(percent * 100.0) / 100.0
                it.coin.pricePurchased = Math.round(it.coin.pricePurchased * 100.0) / 100.0
                it.coinWithUpdate.priceActual = Math.round(it.coinWithUpdate.priceActual * 100.0) / 100.0
            }
            refreshCoinsInWatchlist(ArrayList(it))
            swipeRefreshLayout.isRefreshing = false
        })
    }


    private fun refreshCoinsInWatchlist(coinsWatchlist: ArrayList<CoinWithUpdate>) {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = WatchlistAdapter(coinsWatchlist)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }

}