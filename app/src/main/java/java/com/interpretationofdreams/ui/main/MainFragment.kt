package java.com.interpretationofdreams.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import splitties.experimental.InternalSplittiesApi
import java.com.interpretationofdreams.R
import java.com.interpretationofdreams.data.local.localentity.Words

@AndroidEntryPoint
class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var ui: MainUI
    lateinit var drawerToggle: ActionBarDrawerToggle

    private val mainViewModel: MainViewModel by viewModels()

    @InternalSplittiesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): DrawerLayout {
        ui = MainUI(requireContext())

        return ui.root
    }

    @InternalSplittiesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        mainViewModel.words.observe(viewLifecycleOwner, wordObserver)
    }

    @InternalSplittiesApi
    private fun initView() {
        (activity as AppCompatActivity).setSupportActionBar(ui.toolbar)

        setHasOptionsMenu(true)

        drawerToggle = ActionBarDrawerToggle(
            (activity as AppCompatActivity),
            ui.root,
            ui.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).apply { syncState() }

        ui.root.addDrawerListener(drawerToggle)

        ui.navigationView.setNavigationItemSelectedListener(this)
    }

    @InternalSplittiesApi
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        ui.root.closeDrawers()

        return true
    }

    @InternalSplittiesApi
    private val wordObserver = Observer<List<Words>> { it ->
        val wordAdapter = WordAdapter(it.sortedBy { it.categoryName }).apply {
            listener = {
                val arg = Bundle()
                arg.putInt("wordId", it.id)
                Navigation.findNavController(ui.root)
                    .navigate(R.id.action_nav_main_to_desciptionFragment, arg)
            }
        }

        ui.rvWordsList.adapter = wordAdapter
    }
}