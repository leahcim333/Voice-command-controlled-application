package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import java.util.*

class CommandRecognizer(context: Context, presenter: BasePresenter) {

    private val mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    private val mSpeechRecognizerIntent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

    init {
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

            override fun onError(i: Int) {}

            override fun onResults(bundle: Bundle) {
                val matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null)
                    presenter.processInput(matches)
            }

            override fun onPartialResults(bundle: Bundle) {}

            override fun onEvent(i: Int, bundle: Bundle) {}
        })
    }

    fun startListening() {
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
    }

}