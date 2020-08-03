package java.com.interpretationofdreams.ui.main

import android.content.Context
import android.graphics.Color
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import splitties.dimensions.dp
import splitties.experimental.InternalSplittiesApi
import splitties.views.*
import splitties.views.dsl.appcompat.toolbar
import splitties.views.dsl.core.*
import splitties.views.dsl.material.appBarLayout
import splitties.views.dsl.material.navigationView
import splitties.views.dsl.recyclerview.recyclerView
import java.com.interpretationofdreams.R
import java.com.interpretationofdreams.util.drawerLayout
import java.com.interpretationofdreams.util.lParams
import kotlin.contracts.ExperimentalContracts

class MainUI(override val ctx: Context) : Ui {

    //AppBarMain
    val toolbar = toolbar {
        title = ctx.getString(R.string.app_name)
        setTitleTextColor(Color.WHITE)
    }

    val appBarLayout = appBarLayout {
        add(toolbar, lParams(matchParent, dp(56).toInt()))
    }

    val rvWordsList = recyclerView {
        layoutManager = LinearLayoutManager(ctx)
        itemAnimator = DefaultItemAnimator()
    }
    val etSearch = editText {
        hint = "Type to find"
        setTextColor(Color.BLACK)
        textSize = 14f
    }

    val contentMain = verticalLayout {
        add(etSearch, lParams(matchParent, wrapContent))
        add(rvWordsList, lParams(matchParent, matchParent))
    }

    val appBarMain = verticalLayout {
        add(appBarLayout, lParams(matchParent, dp(56).toInt()))
        add(contentMain, lParams(matchParent, matchParent))
    }

    val header = verticalLayout(theme = R.style.ThemeOverlay_AppCompat_Dark) {
        gravity = gravityBottom
        setPadding(dp(16).toInt())
        backgroundColor = Color.BLUE

        add(imageView {
            setImageResource(R.drawable.ic_baseline_home_24)
            topPadding = dp(8).toInt()
        }, lParams())

        add(textView { textResource = R.string.app_name }, lParams())
    }

    val navigationView = navigationView {
        addHeaderView(header)
        inflateMenu(R.menu.main_drawer)
    }

    @OptIn(ExperimentalContracts::class)
    @InternalSplittiesApi
    override val root = drawerLayout {
        add(appBarMain, lParams(height = matchParent, width = matchParent))

        add(navigationView, lParams(width = wrapContent, height = matchParent) {
            gravity = gravityStart
        })
    }
}