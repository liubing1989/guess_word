package com.example.guess.ui.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guess.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private val TAG = "GameFragment"


    private lateinit var mBinding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentGameBinding.inflate(inflater, container, false)
        Log.i(TAG, "onCreateView: ")
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            mBinding.scoreText.text = newScore.toString()

        }

        viewModel.word.observe(viewLifecycleOwner) { newWord ->
            mBinding.wordText.text = newWord.toString()
        }

        viewModel.gameFinished.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished) {
                val action = GameFragmentDirections.actionToScoreFragment()
                action.score = viewModel.score.value ?: 0
                findNavController().navigate(action)
                viewModel.onGameFishedHasCompleted()
            }
        }

        viewModel.currentTimeString.observe(viewLifecycleOwner) { currentTimes ->
            mBinding.timerText.text = currentTimes
        }

        viewModel.buzz.observe(viewLifecycleOwner){ buzzType->
            if (buzzType!=GameViewModel.BuzzType.NO_BUZZ){
                Log.i(TAG, "onCreateView: buzz")
                buzz(buzzType.pattern)
                viewModel.buzzCompleted()
            }
        }

        mBinding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }

        mBinding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        return mBinding.root
    }
    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }
}