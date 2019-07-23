package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class Speaker(applicationContext: Context) {

    private lateinit var mTextToSpeech: TextToSpeech

    init {
        mTextToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTextToSpeech.language = Locale.getDefault()
            }
        })
    }

    val isSpeaking: Boolean
        get() {
            return mTextToSpeech.isSpeaking
        }


    fun speak(message: CharSequence?) {
        mTextToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}