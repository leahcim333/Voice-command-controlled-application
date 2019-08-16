package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions

import android.view.GestureDetector
import android.view.MotionEvent
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

class CommandActivatorGestureListener(private val view: BaseView) :
    GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        view.onDoubleTap()
        return true
    }
}