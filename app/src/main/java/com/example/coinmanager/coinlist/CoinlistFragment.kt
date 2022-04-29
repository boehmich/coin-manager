package com.example.coinmanager.coinlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinmanager.R
import com.example.coinmanager.adapter.CoinlistAdapter
import com.example.coinmanager.models.CoinlistCoin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinlistFragment : Fragment(R.layout.coinlist_fragment) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: CoinlistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewCoinlist)
        viewModel.updateCoinsCoinlist();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshCoinlist)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateCoinsCoinlist()
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