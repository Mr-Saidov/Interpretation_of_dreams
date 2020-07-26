package java.com.interpretationofdreams.ui.description

import android.content.Context
import android.graphics.Color
import androidx.core.view.setPadding
import splitties.dimensions.dp
import splitties.views.dsl.core.*
import splitties.views.gravityCenter

class DescriptionUI(override val ctx: Context) : Ui {

    var tvDescriptions = textView {
        textSize = 20f
        setPadding(dp(16).toInt())
        setTextColor(Color.BLACK)
    }

    override val root = frameLayout {
        add(tvDescriptions, lParams {
            gravity = gravityCenter
        })
    }
}