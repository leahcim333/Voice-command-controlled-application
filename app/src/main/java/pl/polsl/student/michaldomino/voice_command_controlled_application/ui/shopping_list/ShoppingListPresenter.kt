package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list.ShoppingListInitialCS

class ShoppingListPresenter(override val view: ShoppingListContract.View) : ShoppingListContract.Presenter(view) {

    private val REQUEST_CODE_SPEECH_RECOGNIZRER = 0

    private val REQUEST_CODE_LIST_ELEMENTS_TO_ADD = 1

    override var currentState =
        ShoppingListInitialCS(
            this
        )

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun askForInput(message: String?) {
        speaker.speakInForeground(message)
        view.startSpeechRecognizer(REQUEST_CODE_SPEECH_RECOGNIZRER, message)
    }

    override fun onDoubleTap() {
        currentState = ShoppingListInitialCS(this)
        currentState.initialize()
    }

    override fun processInput(data: Intent) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val userInput = possibleMatches[0]
        currentState.processInput(userInput)
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
        view.startSpeechRecognizer(REQUEST_CODE_LIST_ELEMENTS_TO_ADD, R.string.list_elements)
    }


}