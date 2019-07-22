package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Context

interface BaseView {

    fun getApplicationContext(): Context

    fun showToast(message: String?)

    fun getString(resId: Int): String
}