package com.example.coinmanager.coinlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.coinmanager.R

class CoinlistFragment : Fragment(R.layout.coinlist_fragment) {

    companion object {
        fun newInstance() = CoinlistFragment()
    }

    private lateinit var viewModel: CoinlistViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       var button = view.findViewById<Button>(R.id.buttonAddCoin)

        button.setOnClickListener {
            val navHostFragment = findNavController()
            navHostFragment.navigate(CoinlistFragmentDirections.actionCoinlistFragmentToCoinFragment())
        }
    }


}