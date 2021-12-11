package com.example.coinmanager.coinlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinmanager.R
import com.example.coinmanager.watchlist.WatchlistViewModel

class CoinlistFragment : Fragment(R.layout.coinlist_fragment) {

    private val viewModel: CoinlistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       var button = view.findViewById<Button>(R.id.buttonAddCoin)

        button.setOnClickListener {
            //viewModel.createDummies()
            viewModel.getCoinsFromWebservice()
            Toast.makeText(requireContext(), "Durch", Toast.LENGTH_SHORT).show()
            /*
            val navHostFragment = findNavController()
            navHostFragment.navigate(CoinlistFragmentDirections.actionCoinlistFragmentToCoinFragment())

             */
        }
    }


}