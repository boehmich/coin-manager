package com.example.coinmanager.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.R


class CoinWatchlistViewHolder(val listItemContactRootView: View): RecyclerView.ViewHolder(listItemContactRootView) {
    val coinExchangeTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinExchange)
    val coinNameTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinName)
    val coinSymbolTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinSymbol)
    val coinsDatePurchasedTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinDatePurchased)
    val coinPricePurchasedTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPricePurchased)
    val coinPriceActualTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPriceActual)
    val coinPriceChangedPercentTextView: TextView = listItemContactRootView.findViewById(R.id.tvCoinPercentChange)
}

class WatchlistAdapter(private var coins: List<CoinWithUpdate>,
                       private val onClickListener: (View, CoinWithUpdate) -> Unit
): RecyclerView.Adapter<CoinWatchlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinWatchlistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemContactRootView =
            layoutInflater.inflate(R.layout.list_item_coin_watchlist, parent, false)
        return CoinWatchlistViewHolder(listItemContactRootView)
    }

    override fun onBindViewHolder(holder: CoinWatchlistViewHolder, position: Int) {
        val coin = coins[position]
        holder.coinExchangeTextView.text = coin.coin.exchange
        holder.coinNameTextView.text = coin.coinWithUpdate.name
        holder.coinSymbolTextView.text = coin.coinWithUpdate.symbol
        holder.coinsDatePurchasedTextView.text = coin.coin.datePurchased.slice(0..9)
        holder.coinPricePurchasedTextView.text = String.format("%.2f€", coin.coin.pricePurchased)
        holder.coinPriceActualTextView.text = String.format("%.2f€", coin.coinWithUpdate.priceActual)
        holder.coinPriceChangedPercentTextView.text = String.format("%.2f%%",coin.coin.priceChangedPercent)

        val percentTextColor = getResourceColor(coin.coin.priceChangedPercent)
        holder.coinPriceChangedPercentTextView.setTextColor(ContextCompat.getColor(holder.coinPriceChangedPercentTextView.context, percentTextColor))


        holder.listItemContactRootView.setOnClickListener{ view ->
            onClickListener(view, coin)
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