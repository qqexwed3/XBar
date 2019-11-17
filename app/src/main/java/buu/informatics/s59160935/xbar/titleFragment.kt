package buu.informatics.s59160935.xbar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160935.xbar.databinding.TitleFragmentBinding
import timber.log.Timber


class titleFragment : Fragment() {

    companion object {
        fun newInstance() = titleFragment()
    }

    private lateinit var viewModel: TitleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<TitleFragmentBinding>(inflater,
            R.layout.title_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(TitleViewModel::class.java)
        binding.playButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
        Timber.i("onCreate called")
        return binding.root
    }


}

