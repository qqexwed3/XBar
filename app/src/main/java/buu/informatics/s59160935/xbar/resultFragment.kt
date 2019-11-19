package buu.informatics.s59160935.xbar

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
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

        val args = gameFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context, "score: ${args.score}", Toast.LENGTH_LONG).show()

        binding.homeButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        binding.playAgainButton.setOnClickListener { view : View ->
            view.findNavController().navigate(resultFragmentDirections.actionResultFragmentToGameFragment(args.score))
        }



        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun getShareIntent() : Intent {
        val args = gameFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_success_text, args.score.toString()))
        return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}
