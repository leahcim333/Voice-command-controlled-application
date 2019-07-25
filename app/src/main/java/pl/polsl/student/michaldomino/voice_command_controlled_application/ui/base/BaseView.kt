package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Context
import android.content.Intent
import android.widget.Toast

interface BaseView {

    fun getApplicationContext(): Context

    fun getString(resId: Int): String

    fun startSpeechRecognizer(requestCode: Int, message: String?)

    fun startActivity(intent: Intent)

    fun startActivityFromClass(cls: Class<*>) {
        val intent = Intent(getApplicationContext(), cls)
        startActivity(intent)
    }

    fun showToast(message: String?) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show()
    }
}