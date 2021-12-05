package com.example.coinmanager.coinlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinmanager.R

class CoinlistFragment : Fragment() {

    companion object {
        fun newInstance() = CoinlistFragment()
    }

    private lateinit var viewModel: CoinlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.coinlist_fragment, container, false)
    }


}