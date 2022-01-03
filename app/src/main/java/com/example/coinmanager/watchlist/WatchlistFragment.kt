package com.example.coinmanager.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.R
import com.example.coinmanager.adapter.WatchlistAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.Context
import android.view.ContextThemeWrapper
import androidx.navigation.fragment.findNavController
import com.example.coinmanager.repository


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
        viewModel.readCoinsWatchlist.observe(viewLifecycleOwner, {
            refreshCoinsInWatchlist(ArrayList(it))
            swipeRefreshLayout.isRefreshing = false
        })
    }


    private fun refreshCoinsInWatchlist(coinsWatchlist: ArrayList<CoinWithUpdate>) {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = WatchlistAdapter(coinsWatchlist, onClickListener = this::handleClickListener)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }

    private fun handleClickListener(view: View, coin: CoinWithUpdate) {
        val materialAlertContext: Context = ContextThemeWrapper(requireContext(), R.style.Theme_CoinManager)
        val options = arrayOf("Bearbeiten", "Löschen")
        MaterialAlertDialogBuilder(materialAlertContext)
            .setTitle("Wähle eine Aktion für ${coin.coinWithUpdate.name} aus:")
            .setItems(options) { _, selectedItem ->
                if(selectedItem == 0){
                    val navHostFragment = findNavController()
                    navHostFragment.navigate(WatchlistFragmentDirections.actionWatchlistFragmentToCoinEditFragment(coin))
                }
                if(selectedItem == 1){
                    deleteCoin(coin, materialAlertContext)
                }
            }
            .setCancelable(true)
            .show()
    }

    private fun deleteCoin(coin: CoinWithUpdate, context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle(coin.coinWithUpdate.name)
            .setMessage("Wollen Sie den Coin wirklich löschen?")
            .setPositiveButton("Löschen") { _, _ ->
                repository.deleteCoin(coin.coin)
                Toast.makeText(context, "Der Coin wurde erfolgreich aus der Watchlist gelöscht.", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Abbruch") { _, _ ->
                Toast.makeText(context, "Der Coin ist weiterhin in der Watchlist", Toast.LENGTH_LONG).show()
            }
            .setCancelable(false)
            .show()
    }
}

/*
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

        */