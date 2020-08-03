package java.com.interpretationofdreams.ui.intro

import android.content.Context
import splitties.views.dsl.core.*
import splitties.views.gravityCenter

/**
 *  Created by Dilshodbek on 8/3/2020
 */

class IntroUI(override val ctx: Context) : Ui {
    override val root = frameLayout {
        isClickable = true
        isFocusable = true
        add(textView {
            text = "Intro Fragment"
        }, lParams {
            gravity = gravityCenter
        })
    }
}