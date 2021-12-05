package com.example.coinmanager.coin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.coinmanager.R

class CoinFragment : Fragment(R.layout.coin_fragment) {

    companion object {
        fun newInstance() = CoinFragment()
    }

    private lateinit var viewModel: CoinViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.buttonSaveCoin)

        button.setOnClickListener{
            val navHostFragment = findNavController()
            navHostFragment.navigate(CoinFragmentDirections.actionCoinFragmentToWatchlistFragment())
        }
    }

}