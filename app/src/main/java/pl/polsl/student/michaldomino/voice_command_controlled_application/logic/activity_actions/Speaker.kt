package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions

import android.speech.tts.TextToSpeech
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView
import java.util.*

class Speaker(val view: BaseView) {

    private lateinit var mTextToSpeech: TextToSpeech

    private var disposable: Disposable? = null

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

    fun speakAndRunAction(message: CharSequence?, function: () -> Unit) {
        disposable?.dispose()
        disposable =
            Completable.fromCallable { speakInForeground(message) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose { mTextToSpeech.stop() }
                .subscribe { function() }
    }
}