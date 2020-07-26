package java.com.interpretationofdreams.ui.main

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.view.setPadding
import splitties.dimensions.dp
import splitties.views.backgroundColor
import splitties.views.dsl.core.*
import java.com.interpretationofdreams.util.selectableItemBackground

class WordItemUI(override val ctx: Context) : Ui {
    companion object {
        const val tvWord = 1
    }

    override val root = verticalLayout {
        this.selectableItemBackground()

        add(textView {
            setPadding(4)
            id = tvWord
            textSize = 16f
            setPadding(dp(8).toInt())
            setTextColor(Color.BLACK)
        }, lParams(matchParent, wrapContent))

        add(horizontalLayout {
            backgroundColor = Color.parseColor("#dedede")
        }, lParams(matchParent, dp(1).toInt()))

        layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
    }
}