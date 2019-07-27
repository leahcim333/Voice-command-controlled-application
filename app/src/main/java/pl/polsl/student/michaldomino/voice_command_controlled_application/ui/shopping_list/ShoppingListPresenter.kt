package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list.ShoppingListInitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.RowItem

class ShoppingListPresenter(override val view: ShoppingListContract.View) : ShoppingListContract.Presenter(view) {

    private val REQUEST_CODE_SPEECH_RECOGNIZRER = 0

    override val initialState: CSRoot = ShoppingListInitialCS(this)

    override var currentState: BaseCommandState = initialState

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun askForInput(message: String?) {
        speaker.speakInForeground(message)
        view.startSpeechRecognizer(REQUEST_CODE_SPEECH_RECOGNIZRER, message)
    }

    override fun onDoubleTap() {
        currentState = initialState
        currentState.initialize()
    }

    override fun processInput(data: Intent) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val userInput = possibleMatches[0]
        currentState.processInput(userInput)
    }

    override fun addItems(userInput: String) {
        val resourceDelimiter = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        val elements: List<String> = userInput.split(fullDelimiter)
        for (element in elements) {
            view.addRow(element)
        }
    }

    override fun getItems(): MutableList<RowItem> {
        return view.getItems()
    }

    override fun setNewItemName(item: RowItem, newName: String) {
        view.setNewItemName(item, newName)
    }
}