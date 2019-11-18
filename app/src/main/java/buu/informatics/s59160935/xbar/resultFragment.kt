package buu.informatics.s59160935.xbar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160935.xbar.databinding.GameFragmentBinding
import buu.informatics.s59160935.xbar.databinding.ResultFragmentBinding


class resultFragment : Fragment() {

    companion object {
        fun newInstance() = resultFragment()
    }

    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ResultFragmentBinding>(inflater,
            R.layout.result_fragment,container,false)

        binding.homeButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        binding.playAgainButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
