package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.gesture_listener

import android.view.GestureDetector
import android.view.MotionEvent
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

class CommandActivatorGestureListener(private val presenter: BasePresenter) :
    GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        presenter.onDoubleTap()
        return true
    }
}