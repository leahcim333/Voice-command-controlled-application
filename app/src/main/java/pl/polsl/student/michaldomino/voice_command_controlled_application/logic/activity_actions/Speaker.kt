package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions

import android.speech.tts.TextToSpeech
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView
import java.util.*

class Speaker(val view: BaseView) {

    private lateinit var mTextToSpeech: TextToSpeech

    init {
        create()
    }

    private fun create() {
        mTextToSpeech =
            TextToSpeech(view.getApplicationContext(), TextToSpeech.OnInitListener { status ->
                if (status != TextToSpeech.ERROR) {
                    //if there is no error then set language
                    mTextToSpeech.language = Locale.getDefault()
                    view.onSpeakerReady()
                }
            })
    }

    fun speakInForeground(message: CharSequence?) {
        mTextToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, "a")
        while (!mTextToSpeech.isSpeaking) {
        }
        while (mTextToSpeech.isSpeaking) {
        }
    }
}