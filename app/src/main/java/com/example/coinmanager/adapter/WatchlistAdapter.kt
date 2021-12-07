package com.example.coinmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmanager.CoinApi
import com.example.coinmanager.R


class CoinWatchlistViewHolder(val listItemContactRootView: View): RecyclerView.ViewHolder(listItemContactRootView) {
    val coinIdTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinId)
    val coinNameTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinName)
    val coinSymbolTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinSymbol)
    val coinPricePurchasedTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPricePurchased)
    val coinPriceActualTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPriceActual)
    val coinPriceChangedPercentTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPercentChange)
}

class WatchlistAdapter(private var coins: ArrayList<CoinApi>): RecyclerView.Adapter<CoinWatchlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinWatchlistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemContactRootView =
            layoutInflater.inflate(R.layout.list_item_coin_watchlist, parent, false)
        return CoinWatchlistViewHolder(listItemContactRootView)
    }

    override fun onBindViewHolder(holder: CoinWatchlistViewHolder, position: Int) {
        val coin = coins[position]
        holder.coinIdTextView.text = coin.id.toString()
        holder.coinNameTextView.text = coin.name
        holder.coinSymbolTextView.text = coin.symbol
        holder.coinPricePurchasedTextView.text = "TEST"
        holder.coinPriceActualTextView.text = coin.priceActual.toString()
        holder.coinPriceChangedPercentTextView.text = "TEST"
        /*
        holder.listItemContactRootView.setOnClickListener{
            val navHostFragment = holder.listItemContactRootView.findNavController()
            navHostFragment.navigate(MainFragmentDirections.actionOverviewFragmentToDetailFragment(contact.id))
        }

         */
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}