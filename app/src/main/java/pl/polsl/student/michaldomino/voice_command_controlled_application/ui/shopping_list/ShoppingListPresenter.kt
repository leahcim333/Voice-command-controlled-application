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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDoubleTap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun runCommand(data: Intent, requestCode: Int) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val userInput = possibleMatches[0]
        when (requestCode) {
            REQUEST_CODE_COMMAND_RECOGNITION -> {
                currentState.performCommand(userInput)
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
        val delimiterBuilder = StringBuilder()
        val delimiter = delimiterBuilder.append(" ").append(R.string.elements_delimiter).append(" ").toString()
        val elements: List<String> = userInput.split(delimiter)
        for (element in elements) {
            view.addRow(element)
        }
    }

    override fun getString(resId: Int): String {
        return view.getString(resId)
    }

    override fun initializeAddingElements() {
        speaker.speak(view.getString(R.string.list_elements))
        view.startCommandRecognizer(REQUEST_CODE_LIST_ELEMENTS_TO_ADD, R.string.list_elements)
    }


}