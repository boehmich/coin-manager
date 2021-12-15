package com.example.coinmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.CoinlistCoin
import com.example.coinmanager.R
import com.example.coinmanager.coinlist.CoinlistFragmentDirections


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
        holder.coinPercent1hTextView.text = coin.percent1h.toString() + "%"
        holder.coinPercent24hTextView.text = coin.percent24h.toString() + "%"
        holder.coinPercent7dTextView.text = coin.percent7d.toString()+ "%"
        holder.coinPriceTextView.text = coin.price.toString()+ "€"


        if(coin.percent1h!! < 0){
            holder.coinPercent1hTextView.setTextColor(ContextCompat.getColor(holder.coinPercent1hTextView.context, R.color.negative))
        }
        else{
            holder.coinPercent1hTextView.setTextColor(ContextCompat.getColor(holder.coinPercent1hTextView.context, R.color.positive))
        }

        if(coin.percent24h!! < 0){
            holder.coinPercent24hTextView.setTextColor(ContextCompat.getColor(holder.coinPercent1hTextView.context, R.color.negative))
        }
        else{
            holder.coinPercent24hTextView.setTextColor(ContextCompat.getColor(holder.coinPercent1hTextView.context, R.color.positive))
        }

        if(coin.percent7d!! < 0){
            holder.coinPercent7dTextView.setTextColor(ContextCompat.getColor(holder.coinPercent1hTextView.context, R.color.negative))
        }
        else{
            holder.coinPercent7dTextView.setTextColor(ContextCompat.getColor(holder.coinPercent1hTextView.context, R.color.positive))
        }

        holder.listItemContactRootView.setOnClickListener{
            val navHostFragment = holder.listItemContactRootView.findNavController()
            navHostFragment.navigate(CoinlistFragmentDirections.actionCoinlistFragmentToCoinFragment(coin))
        }
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}