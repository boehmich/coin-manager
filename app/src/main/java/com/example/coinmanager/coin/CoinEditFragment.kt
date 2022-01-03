package com.example.coinmanager.coin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
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
import java.util.*

class CoinEditFragment : Fragment(R.layout.coin_fragment) {

    private val viewModel: CoinEditViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coin = viewModel.selectedCoin

        val tvCoinName: TextView = view.findViewById(R.id.tvCoinFragmentName)
        tvCoinName.setText(coin.coinWithUpdate.name)

        val etExchange: TextInputEditText = view.findViewById(R.id.etCoinFragmentExchange)
        etExchange.setText(coin.coin.exchange)

        etExchange.doAfterTextChanged { newExchange ->
            if(newExchange.toString().isNotBlank()){
                viewModel.coinExchangeChanged(newExchange.toString())
            }
            else{
                Toast.makeText(requireContext(), "Bitte geben Sie eine Börse an!", Toast.LENGTH_SHORT).show()
            }
        }

        val etPrice: TextInputEditText = view.findViewById(R.id.etCoinFragmentPrice)
        etPrice.setText(coin.coin.pricePurchased.toString())

        etPrice.doAfterTextChanged { newPrice ->
            if(newPrice.toString().isNotBlank()){
                viewModel.coinPriceChanged(newPrice.toString().toDouble())
            }
            else{
                Toast.makeText(requireContext(), "Bitte geben Sie einen Preis ein!", Toast.LENGTH_SHORT).show()
            }
        }

        val etDate: TextView = view.findViewById(R.id.etCoinFragmentDate)

        val date = coin.coin.datePurchased
        val year = date.slice(6..9).toInt()
        val month = date.slice(3..4).toInt()
        val day = date.slice(0..1).toInt()
        val hour = date.slice(11..12).toInt()
        val minutes = date.slice(14..15).toInt()

        val currentDate = String.format("%02d.%02d.%04d %02d:%02d", day, month, year, hour, minutes)
        etDate.setText(currentDate)

        etDate.setOnClickListener {
            var time = ""

            DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                val date = String.format("%02d.%02d.%04d", mDay, mMonth, mYear)
                etDate.setText("${date} ${time}")
            }, year, month, day)
                .show()

            TimePickerDialog(requireContext(), { view, mHour, mMinutes ->
                time = String.format("%02d:%02d", mHour, mMinutes)
            }, hour, minutes, true )
                .show()
        }

        etDate.doAfterTextChanged { newDate ->
            viewModel.coinDateChanged(newDate.toString())
            Toast.makeText(requireContext(), newDate, Toast.LENGTH_SHORT).show()
        }

        val button = view.findViewById<Button>(R.id.buttonSaveCoin)

        button.setOnClickListener{
            viewModel.updateCoin()
            val navHostFragment = findNavController()
            navHostFragment.navigate(CoinEditFragmentDirections.actionCoinEditFragmentToWatchlistFragment())
        }

    }

}