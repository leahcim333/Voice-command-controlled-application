package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.model.shopping_list.ShoppingListInitialCS

class ShoppingListPresenter(override val view: ShoppingListContract.View) : ShoppingListContract.Presenter(view) {

    private val REQUEST_CODE_COMMAND_RECOGNITION = 0

    private val REQUEST_CODE_LIST_ELEMENTS_TO_ADD = 1

    private var currentState = ShoppingListInitialCS(this)

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun onDoubleTap() {
        currentState =
            ShoppingListInitialCS(
                this
            )
        speaker.speakInForeground(getString(R.string.tell_command))
        view.startCommandRecognizer(REQUEST_CODE_COMMAND_RECOGNITION, R.string.tell_command)
    }

    override fun runCommand(data: Intent, requestCode: Int) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val userInput = possibleMatches[0]
        when (requestCode) {
            REQUEST_CODE_COMMAND_RECOGNITION -> {
                currentState.processInput(userInput)
            }
            REQUEST_CODE_LIST_ELEMENTS_TO_ADD -> {
                addElements(userInput)
            }
            else -> {
            }
        }
    }

    private fun addElements(userInput: String) {
        val resourceDelimiter = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        val elements: List<String> = userInput.split(fullDelimiter)
        for (element in elements) {
            view.addRow(element)
        }
    }

    override fun initializeAddingElements() {
        speaker.speakInForeground(view.getString(R.string.list_elements))
        view.startCommandRecognizer(REQUEST_CODE_LIST_ELEMENTS_TO_ADD, R.string.list_elements)
    }


}