package com.example.coinmanager.coinlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.CoinlistCoin
import com.example.coinmanager.R
import com.example.coinmanager.adapter.CoinlistAdapter
import com.example.coinmanager.adapter.WatchlistAdapter
import com.example.coinmanager.watchlist.WatchlistViewModel

class CoinlistFragment : Fragment(R.layout.coinlist_fragment) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: CoinlistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewCoinlist)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshCoinlist)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.createCoinsCoinlist()
        }

        viewModel.coinlist.observe(viewLifecycleOwner){
            refreshCoinsInWatchlist(ArrayList(it))
           swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun refreshCoinsInWatchlist(coinsCoinlist: ArrayList<CoinlistCoin>) {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = CoinlistAdapter(coinsCoinlist)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }



}


/**
var button = view.findViewById<Button>(R.id.buttonAddCoin)

button.setOnClickListener {
//viewModel.createDummies()
viewModel.getCoinsFromWebservice()
Toast.makeText(requireContext(), "Durch", Toast.LENGTH_SHORT).show()
/*
val navHostFragment = findNavController()
navHostFragment.navigate(CoinlistFragmentDirections.actionCoinlistFragmentToCoinFragment())

}
}
 */