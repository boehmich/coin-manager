package com.example.coinmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.CoinlistCoin
import com.example.coinmanager.R


class CoinCoinlistViewHolder(val listItemContactRootView: View): RecyclerView.ViewHolder(listItemContactRootView) {
    val coinIdTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinlistId)
    val coinNameTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinlistName)
    val coinSymbolTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinlistSymbol)
    val coinPriceTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinlistPrice)
    val coinPercent1hTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinlistPercent1h)
    val coinPercent24hTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinlistPercent24h)
    val coinPercent7dTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinlistPercent7d)
}

class CoinlistAdapter(private var coins: List<CoinlistCoin>): RecyclerView.Adapter<CoinCoinlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinCoinlistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemContactRootView =
            layoutInflater.inflate(R.layout.list_item_coin_coinlist, parent, false)
        return CoinCoinlistViewHolder(listItemContactRootView)
    }

    override fun onBindViewHolder(holder: CoinCoinlistViewHolder, position: Int) {
        val coin = coins[position]
        holder.coinIdTextView.text = coin.id.toString()
        holder.coinNameTextView.text = coin.name
        holder.coinSymbolTextView.text = coin.symbol
        holder.coinPercent1hTextView.text = coin.percent1h.toString()
        holder.coinPercent24hTextView.text = coin.percent24h.toString()
        holder.coinPercent7dTextView.text = coin.percent7d.toString()
        holder.coinPriceTextView.text = coin.price.toString()


        holder.listItemContactRootView.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Coin: ${coin.id} ${coin.name}", Toast.LENGTH_LONG).show()
            //val navHostFragment = holder.listItemContactRootView.findNavController()
            //navHostFragment.navigate(MainFragmentDirections.actionOverviewFragmentToDetailFragment(coin.coin.id))
        }

    }

    override fun getItemCount(): Int {
        return coins.size
    }
}