package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

interface BaseView {

    fun getApplicationContext(): Context

    fun getString(resId: Int): String

    fun startActivity(intent: Intent)

    fun finish()

    fun finishAffinity()

    fun speakAndRunAction(message: String, function: () -> Unit)

    fun requestPermission()

    fun onSpeakerReady()

    fun stopActivityActions()

    fun startActivityFromClass(cls: Class<*>) {
        val intent = Intent(getApplicationContext(), cls)
        startActivity(intent)
    }

    fun showToast(message: String?) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun isRecordAudioGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            getApplicationContext(),
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }
}