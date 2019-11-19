package buu.informatics.s59160935.xbar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import buu.informatics.s59160935.xbar.databinding.TitleFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.title_fragment.*
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

        binding.nameTitleText.setOnClickListener { view ->
            Snackbar.make(view, "X BAR GAME!!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        binding.playButton.setOnClickListener { view : View ->
            view.findNavController().navigate(titleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        binding.historyButton.setOnClickListener { view : View ->
            view.findNavController().navigate(titleFragmentDirections.actionTitleFragmentToHistoryFragment())
        }

        Timber.i("onCreate called")
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

}

