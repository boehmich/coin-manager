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
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CoinFragment : Fragment(R.layout.coin_fragment) {

    private val viewModel: CoinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coin = viewModel.selectedCoin

        val tvCoinName: TextView = view.findViewById(R.id.tvCoinFragmentName)
        tvCoinName.setText(coin.name)

        val etExchange: TextInputEditText = view.findViewById(R.id.etCoinFragmentExchange)
        etExchange.doAfterTextChanged { newExchange ->
            if(newExchange.toString().isBlank()){
                Toast.makeText(requireContext(), getString(R.string.enter_exchange), Toast.LENGTH_SHORT).show()
            }
        }

        val etPrice: TextInputEditText = view.findViewById(R.id.etCoinFragmentPrice)
        etPrice.setText(coin.price.toString())

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

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)

        val currentDate = String.format("%02d.%02d.%04d %02d:%02d", day, month + 1, year, hour, minutes)
        etDate.setText(currentDate)

        etDate.setOnClickListener {
            var time = ""

            DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                val date = String.format("%02d.%02d.%04d", mDay, mMonth + 1, mYear)
                etDate.setText(String.format("%1s %2s", date, time))
            }, year, month, day)
                .show()

           TimePickerDialog(requireContext(), { view, mHour, mMinutes ->
                time = String.format("%02d:%02d", mHour, mMinutes)
            }, hour, minutes, true )
                .show()
        }

        val button = view.findViewById<Button>(R.id.buttonSaveCoin)
        button.setText(R.string.add_to_watchlist)
        button.setOnClickListener{
            viewModel.save(etExchange.text.toString(), etDate.text.toString())
            val navHostFragment = findNavController()
            navHostFragment.navigate(CoinFragmentDirections.actionCoinFragmentToWatchlistFragment())
        }
    }

}

/*
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(viewModel.selectedCoin is CoinlistCoin){
            Toast.makeText(requireContext(), "Typ CoinlistCoin", Toast.LENGTH_SHORT).show()
        }
        if(viewModel.selectedCoin is CoinWithUpdate){
            Toast.makeText(requireContext(), "Typ CoinWithUpdate", Toast.LENGTH_SHORT).show()
        }
    }
 */

//val currentDate: String = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date())
