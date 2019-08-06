package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast

interface BaseView {

    fun getApplicationContext(): Context

    fun getString(resId: Int): String

    fun startActivity(intent: Intent)

    fun startActivityForResult(intent: Intent, requestCode: Int)

    fun startSpeechRecognizer(requestCode: Int, messageId: Int) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(messageId))
        try {
            startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            showToast(e.message)
        }
    }

    fun startActivityFromClass(cls: Class<*>) {
        val intent = Intent(getApplicationContext(), cls)
        startActivity(intent)
    }

    fun showToast(message: String?) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show()
    }
}