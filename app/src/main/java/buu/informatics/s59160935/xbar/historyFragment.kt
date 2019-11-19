package buu.informatics.s59160935.xbar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import buu.informatics.s59160935.xbar.database.ScoreDatabase
import buu.informatics.s59160935.xbar.databinding.HistoryFragmentBinding
import buu.informatics.s59160935.xbar.databinding.TitleFragmentBinding
import timber.log.Timber


class historyFragment : Fragment() {

    companion object {
        fun newInstance() = historyFragment()
    }

    private lateinit var viewModel: HistoryViewModel
    private lateinit var viewModelFactory: HistoryViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val application = requireNotNull(this.activity).application
        val dataSource = ScoreDatabase.getInstance(application).scoreDatabaseDAO

        val binding = DataBindingUtil.inflate<HistoryFragmentBinding>(inflater,
            R.layout.history_fragment,container,false)

        viewModelFactory = HistoryViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HistoryViewModel::class.java)

        var adapter = HistoryAdapter()

        binding.listHistory.adapter = adapter

        viewModel.scoreList?.observe(this, Observer {
            //            Timber.i(list.size.toString())
            adapter.submitList(it)
//            Log.i("asd0", it.size.toString())
        })

        return binding.root
    }


}
