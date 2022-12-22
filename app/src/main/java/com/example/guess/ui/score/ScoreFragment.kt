package com.example.guess.ui.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guess.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {
    private lateinit var mBinding: FragmentScoreBinding
    private lateinit var model: ScoreViewModel
    private lateinit var factory: ScoreViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentScoreBinding.inflate(inflater, container, false)
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        mBinding.scoreText.text = scoreFragmentArgs.score.toString()
        factory = ScoreViewModelFactory(scoreFragmentArgs.score)
        model = ViewModelProvider(this,factory)[ScoreViewModel::class.java]
        mBinding.playAgainButton.setOnClickListener { onPlayAgain() }
        return mBinding.root
    }

    private fun onPlayAgain() {
        findNavController().navigate(ScoreFragmentDirections.actionToGameFragment(0))
    }

}