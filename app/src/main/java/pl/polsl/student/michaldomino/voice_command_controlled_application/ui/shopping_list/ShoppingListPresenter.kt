package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.ShoppingListInitialCS

class ShoppingListPresenter(override val view: ShoppingListContract.View) : ShoppingListContract.Presenter {

    private val REQUEST_CODE_COMMAND_RECOGNITION = 0

    private val REQUEST_CODE_LIST_ELEMENTS_TO_ADD = 1

    override var currentState: BaseCommandState = ShoppingListInitialCS(this)

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun onDoubleTap() {
        speaker.speak(getString(R.string.tell_command))
//        var a = speaker.isSpeaking
//        val f = 0
//        var b = speaker.isSpeaking
        while (speaker.isSpeaking) {
        }
        view.startCommandRecognizer(REQUEST_CODE_COMMAND_RECOGNITION, R.string.tell_command)
    }

    override fun runCommand(data: Intent, requestCode: Int) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val userInput = possibleMatches[0]
        when (requestCode) {
            REQUEST_CODE_COMMAND_RECOGNITION -> {
                currentState.performCommand(possibleMatches)
            }
            REQUEST_CODE_LIST_ELEMENTS_TO_ADD -> {
                addElements(userInput)
            }
            else -> {
            }
        }
//        speaker.speak(command)
    }

    private fun addElements(userInput: String) {
        val resourceDelimiter = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        val elements: List<String> = userInput.split(fullDelimiter)
        for (element in elements) {
            view.addRow(element)
        }
    }

    override fun getString(resId: Int): String {
        return view.getString(resId)
    }

    override fun initializeAddingElements() {
        speaker.speak(view.getString(R.string.list_elements))
        while (speaker.isSpeaking) {
        }
        view.startCommandRecognizer(REQUEST_CODE_LIST_ELEMENTS_TO_ADD, R.string.list_elements)
    }


}