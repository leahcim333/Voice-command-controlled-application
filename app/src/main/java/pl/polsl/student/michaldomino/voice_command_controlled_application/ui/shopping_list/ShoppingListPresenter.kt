package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list.ShoppingListInitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.ShoppingListItem

class ShoppingListPresenter(override val view: ShoppingListContract.View) : ShoppingListContract.Presenter(view) {

    private val REQUEST_CODE_SPEECH_RECOGNIZER = 0

    override val initialState: CSRoot = ShoppingListInitialCS(this)

    override var currentState: BaseCommandState = initialState

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun askForInput(messageId: Int) {
        val message: String = view.getString(messageId)
        speaker.speakInForeground(message)
        view.startSpeechRecognizer(REQUEST_CODE_SPEECH_RECOGNIZER, messageId)
    }

    override fun addItems(userInput: String) {
        val resourceDelimiter: String = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        val elements: List<String> = userInput.split(fullDelimiter)
        val existingItems: List<String> = view.getItems().map { it.text }
        elements.filter { it !in existingItems }.forEach { view.addRow(it) }
    }

    override fun getItems(): MutableList<ShoppingListItem> {
        return view.getItems()
    }

    override fun setNewItemName(item: ShoppingListItem, newName: String) {
        val existingItems: List<String> = view.getItems().map { it.text }
        if (newName !in existingItems)
            view.setNewItemName(item, newName)
        else
            speaker.speakInForeground(getString(R.string.item_already_exists))
    }

    override fun speak(message: String) {
        speaker.speakInForeground(message)
    }
}