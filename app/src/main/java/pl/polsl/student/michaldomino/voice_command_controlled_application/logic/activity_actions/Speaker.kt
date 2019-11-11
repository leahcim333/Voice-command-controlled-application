package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import java.util.*


class Speaker(private val view: VoiceCommandsView) {

    private lateinit var mTextToSpeech: TextToSpeech

    init {
        mTextToSpeech =
            TextToSpeech(view.getApplicationContext(), TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTextToSpeech.language = Locale.getDefault()
            }
        })
        mTextToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String) {
                val f = 0
            }

            override fun onDone(utteranceId: String) {
                view.showToast("Finished")
            }

            override fun onError(utteranceId: String) {
            }
        })
    }

    fun speakInForeground(message: CharSequence?) {
//        mTextToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//            override fun onStart(utteranceId: String) {
//
//            }
//
//            override fun onDone(utteranceId: String) {
//                view.showToast("Finished")
//            }
//
//            override fun onError(utteranceId: String) {
//            }
//        })
//        mTextToSpeech.setOnUtteranceProgressListener()

        mTextToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
//        while (!mTextToSpeech.isSpeaking) {
//        }
//        while (mTextToSpeech.isSpeaking) {
//        }
    }
}