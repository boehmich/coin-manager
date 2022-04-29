package com.example.coinmanager.coin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coinmanager.R
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinEditFragment : Fragment(R.layout.coin_fragment) {

    private val viewModel: CoinEditViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("inEditFragment", "YESSSSSSSSSSSS")
        val coin = viewModel.selectedCoin
        Log.e("Coin", "$coin")

        val tvCoinName: TextView = view.findViewById(R.id.tvCoinFragmentName)
        tvCoinName.setText(coin.coinWithUpdate.name)

        val etExchange: TextInputEditText = view.findViewById(R.id.etCoinFragmentExchange)
        etExchange.setText(coin.coin.exchange)

        etExchange.doAfterTextChanged { newExchange ->
            if(newExchange.toString().isNotBlank()){
                viewModel.coinExchangeChanged(newExchange.toString())
            }
            else{
                Toast.makeText(requireContext(), getString(R.string.enter_exchange), Toast.LENGTH_SHORT).show()
            }
        }

        val etPrice: TextInputEditText = view.findViewById(R.id.etCoinFragmentPrice)
        etPrice.setText(coin.coin.pricePurchased.toString())

        etPrice.doAfterTextChanged { newPrice ->
            if(newPrice.toString().isNotBlank()){
                viewModel.coinPriceChanged(newPrice.toString().toDouble())
            }
            else{
                viewModel.coinPriceChanged(0.0)
                Toast.makeText(requireContext(), getString(R.string.enter_price), Toast.LENGTH_SHORT).show()
            }
        }

        val etDate: TextView = view.findViewById(R.id.etCoinFragmentDate)

        val date = coin.coin.datePurchased
        val year = date.slice(6..9).toInt()
        var month = date.slice(3..4).toInt()
        val day = date.slice(0..1).toInt()
        val hour = date.slice(11..12).toInt()
        val minutes = date.slice(14..15).toInt()

        val currentDate = String.format("%02d.%02d.%04d %02d:%02d", day, month, year, hour, minutes)
        etDate.setText(currentDate)

        etDate.setOnClickListener {
            var time = ""

            DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                val date = String.format("%02d.%02d.%04d", mDay, mMonth + 1, mYear)
                etDate.setText(String.format("%1s %2s", date, time))
            }, year, month - 1, day)
                .show()

            TimePickerDialog(requireContext(), { view, mHour, mMinutes ->
                time = String.format("%02d:%02d", mHour, mMinutes)
            }, hour, minutes, true )
                .show()
        }

        etDate.doAfterTextChanged { newDate ->
            viewModel.coinDateChanged(newDate.toString())
        }

        val button = view.findViewById<Button>(R.id.buttonSaveCoin)
        button.setText(R.string.update)
        button.setOnClickListener{
            viewModel.updateCoin()
            val navHostFragment = findNavController()
            navHostFragment.navigate(CoinEditFragmentDirections.actionCoinEditFragmentToWatchlistFragment())
        }

    }

}