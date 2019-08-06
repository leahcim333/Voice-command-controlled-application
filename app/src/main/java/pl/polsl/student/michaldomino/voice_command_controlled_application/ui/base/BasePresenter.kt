package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot

abstract class BasePresenter(protected open val view: BaseView) {

    protected abstract val initialState: CSRoot

    abstract var currentState: BaseCommandState

    abstract fun start()

    abstract fun askForInput(messageId: Int)

    abstract fun speak(message: String)

    fun processInput(data: Intent) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val userInput = possibleMatches[0]
        currentState.processInput(userInput)
    }

    open fun onDoubleTap() {
        currentState = initialState
        currentState.initialize()
    }

    fun getString(resId: Int): String {
        return view.getString(resId)
    }
}