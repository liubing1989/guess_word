package com.example.guess.ui.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guess.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {
    private lateinit var mBinding: FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentTitleBinding.inflate(inflater,container,false)
        mBinding.playGameButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionToGameFragment(0))
        }
        return mBinding.root
    }

}