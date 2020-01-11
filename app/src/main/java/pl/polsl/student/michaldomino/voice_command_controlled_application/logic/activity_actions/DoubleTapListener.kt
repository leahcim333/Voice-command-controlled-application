package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions

import android.view.GestureDetector
import android.view.MotionEvent
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView

class DoubleTapListener(private val view: VoiceCommandsView) :
    GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        view.onDoubleTap()
        return true
    }
}