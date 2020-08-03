package java.com.interpretationofdreams.ui.main

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import splitties.experimental.InternalSplittiesApi
import splitties.views.onClick
import timber.log.Timber
import java.com.interpretationofdreams.R
import java.com.interpretationofdreams.data.local.localentity.Words
import java.com.interpretationofdreams.util.hideSoftInputFromWindow

@AndroidEntryPoint
class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var overallYScroll = 0
    private var overallPosition = 0
    private var lastYScroll = 0
    private var lastPosition = 0
    private var isScroll = false

    lateinit var ui: MainUI
    lateinit var drawerToggle: ActionBarDrawerToggle

    private val mainViewModel: MainViewModel by viewModels()
    private val wordAdapter = WordAdapter()

    @InternalSplittiesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): DrawerLayout {
        ui = MainUI(requireContext())
        isScroll = false
        return ui.root
    }

    @InternalSplittiesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        mainViewModel.pagedWords.observe(viewLifecycleOwner, wordObserver)
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
        ui.rvWordsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                try {

                    val layoutManager = (ui.rvWordsList.layoutManager as LinearLayoutManager)
                    overallPosition =
                        layoutManager.findFirstVisibleItemPosition()
                    val rect = Rect()
                    layoutManager.findViewByPosition(overallPosition)
                        ?.getGlobalVisibleRect(rect)
                    overallYScroll =
                        layoutManager.findViewByPosition(
                            overallPosition
                        )!!.height - rect.height()
                } catch (e: Exception) {

                }
            }
        })
        ui.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                ui.rvWordsList.scrollToPosition(0)
                mainViewModel.setfilter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        wordAdapter.listener = {
            val arg = Bundle()
            arg.putInt("wordId", it.id)
            Navigation.findNavController(ui.root)
                .navigate(R.id.action_nav_main_to_desciptionFragment, arg)
        }
        ui.rvWordsList.adapter = wordAdapter
        ui.rvWordsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                requireActivity().hideSoftInputFromWindow()
            }
        })
        ui.toolbar.onClick { ui.rvWordsList.smoothScrollToPosition(0) }
    }

    @InternalSplittiesApi
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        ui.root.closeDrawers()
        return true
    }

    override fun onResume() {
        super.onResume()
        ui.rvWordsList.scrollToPosition(lastPosition)
        ui.rvWordsList.viewTreeObserver.addOnGlobalLayoutListener {
            if (!isScroll) {
                ui.rvWordsList.scrollBy(lastYScroll, lastYScroll)
                isScroll = true
            }
        }
    }

    @InternalSplittiesApi
    private val wordObserver = Observer<PagedList<Words>> {
        Timber.e("private val wordObserver = Observer<PagedList<Words>> { it -> : %s", it.size)
        wordAdapter.submitList(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lastYScroll = overallYScroll
        mainViewModel.setfilter("")
        lastPosition = overallPosition
    }
}