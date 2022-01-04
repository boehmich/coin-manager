package com.example.coinmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmanager.R
import com.example.coinmanager.coinlist.CoinlistFragmentDirections
import com.example.coinmanager.models.CoinlistCoin


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
        holder.coinPercent1hTextView.text = String.format("%.2f%%", coin.percent1h)
        holder.coinPercent24hTextView.text = String.format("%.2f%%",coin.percent24h)
        holder.coinPercent7dTextView.text = String.format("%.2f%%",coin.percent7d)
        holder.coinPriceTextView.text = String.format("%.5f€",coin.price)

        val percent1hTextColor = getResourceColor(coin.percent1h!!)
        val percent24hTextColor = getResourceColor(coin.percent24h!!)
        val percent7dTextColor = getResourceColor(coin.percent7d!!)

        holder.coinPercent1hTextView.setTextColor(ContextCompat.getColor(holder.coinPercent1hTextView.context, percent1hTextColor))
        holder.coinPercent24hTextView.setTextColor(ContextCompat.getColor(holder.coinPercent24hTextView.context, percent24hTextColor))
        holder.coinPercent7dTextView.setTextColor(ContextCompat.getColor(holder.coinPercent7dTextView.context, percent7dTextColor))


        holder.listItemContactRootView.setOnClickListener{
            val navHostFragment = holder.listItemContactRootView.findNavController()
            navHostFragment.navigate(CoinlistFragmentDirections.actionCoinlistFragmentToCoinFragment(coin))
        }
    }

    private fun getResourceColor(number: Double): Int {
        var stringResourceColor: Int = R.color.black

        if(number < 0) stringResourceColor = R.color.negative
        if(number > 0) stringResourceColor = R.color.positive

        return stringResourceColor
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}