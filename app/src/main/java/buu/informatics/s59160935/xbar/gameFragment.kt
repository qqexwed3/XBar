package buu.informatics.s59160935.xbar

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import buu.informatics.s59160935.xbar.databinding.GameFragmentBinding


class gameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<GameFragmentBinding>(inflater,
            R.layout.game_fragment,container,false)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        viewModel.eventEndGame.observe(this, Observer<Boolean>{
            if(it)
            findNavController().navigate(gameFragmentDirections.actionGameFragmentToResultFragment(viewModel.score.value?:0))
        })
        //setHasOptionsMenu(true)

        binding.lifecycleOwner = this
        binding.gameViewModel = viewModel
        return binding.root
    }
}
