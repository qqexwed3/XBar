package buu.informatics.s59160935.xbar

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import buu.informatics.s59160935.xbar.databinding.GameFragmentBinding


class gameFragment : Fragment() {

    companion object {
        fun newInstance() = gameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<GameFragmentBinding>(inflater,
            R.layout.game_fragment,container,false)

        binding.choice1Button.setOnClickListener { view : View ->
//            view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            view.findNavController().navigate(gameFragmentDirections.actionGameFragmentToResultFragment(0))
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    }


}
