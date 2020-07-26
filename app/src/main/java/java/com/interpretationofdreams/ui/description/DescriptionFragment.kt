package java.com.interpretationofdreams.ui.description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import java.com.interpretationofdreams.data.local.localentity.Descriptions

@AndroidEntryPoint
class DescriptionFragment : Fragment() {

    private lateinit var ui: DescriptionUI
    private val viewModel: DescriptionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = DescriptionUI(requireContext())

        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.getDescription(it.getInt("wordId"))
        }

        viewModel.description.observe(viewLifecycleOwner, descriptionObserve)
    }

    private val descriptionObserve = Observer<List<Descriptions>> { it ->
        var descriptions = ""

        it.forEach {
            descriptions += it.txt + "\n"
        }

        ui.tvDescriptions.text = descriptions
    }
}