package java.com.interpretationofdreams.util

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.drawerlayout.widget.DrawerLayout
import splitties.experimental.InternalSplittiesApi
import splitties.views.dsl.core.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

//DrawerLayout
@InternalSplittiesApi
@ExperimentalContracts
inline fun Ui.drawerLayout(
    @IdRes id: Int = View.NO_ID,
    @StyleRes theme: Int = NO_THEME,
    initView: DrawerLayout.() -> Unit = {}
): DrawerLayout {
    contract { callsInPlace(initView, InvocationKind.EXACTLY_ONCE) }
    return ctx.drawerLayout(id, theme, initView)
}

@ExperimentalContracts
inline fun DrawerLayout.defaultLParams(
    width: Int = matchParent,
    height: Int = matchParent,
    initParams: DrawerLayout.LayoutParams.() -> Unit = {}
): DrawerLayout.LayoutParams {
    contract { callsInPlace(initParams, InvocationKind.EXACTLY_ONCE) }
    return DrawerLayout.LayoutParams(width, height).apply(initParams)
}

@ExperimentalContracts
inline fun DrawerLayout.lParams(
    width: Int = matchParent,
    height: Int = wrapContent,
    initParams: DrawerLayout.LayoutParams.() -> Unit = {}
): DrawerLayout.LayoutParams {
    contract { callsInPlace(initParams, InvocationKind.EXACTLY_ONCE) }
    return DrawerLayout.LayoutParams(width, height).apply(initParams)
}

@InternalSplittiesApi
@ExperimentalContracts
inline fun View.drawerLayout(
    @IdRes id: Int = View.NO_ID,
    @StyleRes theme: Int = NO_THEME,
    initView: DrawerLayout.() -> Unit = {}
): DrawerLayout {
    contract { callsInPlace(initView, InvocationKind.EXACTLY_ONCE) }
    return context.drawerLayout(id, theme, initView)
}

@InternalSplittiesApi
@ExperimentalContracts
inline fun Context.drawerLayout(
    @IdRes id: Int = View.NO_ID,
    @StyleRes theme: Int = NO_THEME,
    initView: DrawerLayout.() -> Unit = {}
): DrawerLayout {
    contract { callsInPlace(initView, InvocationKind.EXACTLY_ONCE) }
    return view(::DrawerLayout, id, theme, initView)
}