package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

interface BasePresenter {
    fun start()

    fun getString(resId: Int): String
}