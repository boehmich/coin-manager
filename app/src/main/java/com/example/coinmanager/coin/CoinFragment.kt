package com.example.coinmanager.coin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coinmanager.CoinApi
import com.example.coinmanager.R
import com.google.android.material.textfield.TextInputEditText

class CoinFragment : Fragment(R.layout.coin_fragment) {

    private val viewModel: CoinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var etCoinName: TextInputEditText = view.findViewById(R.id.etCoinName)

        var etPricePurchased: TextInputEditText = view.findViewById(R.id.etPricePurchased)

        val button = view.findViewById<Button>(R.id.buttonSaveCoin)

        button.setOnClickListener{
            var coin = CoinApi(1, etCoinName.text.toString(), "symbol", "slug",etPricePurchased.text.toString().toDouble())
            Log.e("neuer Coin: ", "$coin")
            viewModel.save(coin)
            val navHostFragment = findNavController()
            navHostFragment.navigate(CoinFragmentDirections.actionCoinFragmentToWatchlistFragment())
        }
    }

}