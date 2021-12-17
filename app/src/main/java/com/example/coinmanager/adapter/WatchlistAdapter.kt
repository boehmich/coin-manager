package com.example.coinmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmanager.Coin
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.R
import com.example.coinmanager.coinlist.CoinlistFragmentDirections


class CoinWatchlistViewHolder(val listItemContactRootView: View): RecyclerView.ViewHolder(listItemContactRootView) {
    val coinIdTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinId)
    val coinNameTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinName)
    val coinSymbolTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinSymbol)
    val coinPricePurchasedTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPricePurchased)
    val coinPriceActualTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPriceActual)
    val coinPriceChangedPercentTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPercentChange)
}

class WatchlistAdapter(private var coins: List<CoinWithUpdate>): RecyclerView.Adapter<CoinWatchlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinWatchlistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemContactRootView =
            layoutInflater.inflate(R.layout.list_item_coin_watchlist, parent, false)
        return CoinWatchlistViewHolder(listItemContactRootView)
    }

    override fun onBindViewHolder(holder: CoinWatchlistViewHolder, position: Int) {
        val coin = coins[position]
        holder.coinIdTextView.text = coin.coin.id.toString()
        holder.coinNameTextView.text = coin.coinWithUpdate.name
        holder.coinSymbolTextView.text = coin.coinWithUpdate.symbol
        holder.coinPricePurchasedTextView.text = coin.coin.pricePurchased.toString() + "€"
        holder.coinPriceActualTextView.text = coin.coinWithUpdate.priceActual.toString() + "€"
        holder.coinPriceChangedPercentTextView.text = coin.coin.priceChangedPercent.toString() + "%"

        holder.listItemContactRootView.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Coin: ${coin.coin.id} ${coin.coinWithUpdate.name}", Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {
        return coins.size
    }
}