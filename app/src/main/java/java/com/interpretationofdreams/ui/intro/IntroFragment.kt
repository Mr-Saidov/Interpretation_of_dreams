package java.com.interpretationofdreams.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import splitties.views.onClick
import java.com.interpretationofdreams.R

/**
 *  Created by Dilshodbek on 8/3/2020
 */

class IntroFragment : Fragment() {
    lateinit var ui: IntroUI
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = IntroUI(container!!.context)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.root.onClick {
            Navigation.findNavController(ui.root).navigate(R.id.action_introFragment_to_nav_main)
        }
    }
}