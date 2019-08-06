package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list.ShoppingListInitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.ShoppingListItem
import java.util.*

class ShoppingListPresenter(override val view: ShoppingListContract.View) : ShoppingListContract.Presenter(view) {

    private val REQUEST_CODE_SPEECH_RECOGNIZER = 0

    override val initialState: CSRoot = ShoppingListInitialCS(this)

    override var currentState: BaseCommandState = initialState

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    private lateinit var mSpeechRecognizer: SpeechRecognizer

    private lateinit var mSpeechRecognizerIntent: Intent

    override fun start() {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(view.getApplicationContext())

        mSpeechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )

        mSpeechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(v: Float) {

            }

            override fun onBufferReceived(bytes: ByteArray) {

            }

            override fun onEndOfSpeech() {

            }

            override fun onError(i: Int) {

            }

            override fun onResults(bundle: Bundle) {
                //getting all the matches
                val matches = bundle
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

                //displaying the first match
                if (matches != null)
                    speaker.speakInForeground(matches[0])
            }

            override fun onPartialResults(bundle: Bundle) {

            }

            override fun onEvent(i: Int, bundle: Bundle) {

            }
        })

    }

    override fun onDoubleTap() {
        view.onDoubleTap()
//        mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
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