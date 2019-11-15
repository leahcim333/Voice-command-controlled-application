package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import java.util.*

class CommandRecognizer(private val view: VoiceCommandsView) {

    private val mSpeechRecognizer: SpeechRecognizer =
        SpeechRecognizer.createSpeechRecognizer(view.getApplicationContext())

    private val mSpeechRecognizerIntent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

    init {
        create()
    }

    private fun create() {
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()
        )

        mSpeechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(v: Float) {}

            override fun onBufferReceived(bytes: ByteArray) {}

            override fun onEndOfSpeech() {}

            override fun onError(i: Int) {
                when (i) {
                    SpeechRecognizer.ERROR_SERVER -> view.onSpeechRecognizerServerError()
                }
            }

            override fun onResults(bundle: Bundle) {
                view.onCommandRecognizerResults(bundle)
            }

            override fun onPartialResults(bundle: Bundle) {}

            override fun onEvent(i: Int, bundle: Bundle) {}
        })
    }

    fun startListening() {
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
    }

    fun cancelListening() {
        mSpeechRecognizer.cancel()
    }
}