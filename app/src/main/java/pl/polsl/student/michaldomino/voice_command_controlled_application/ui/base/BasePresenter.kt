package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

interface BasePresenter {

    fun create()

    fun stop()

    fun onPermissionGranted()

    fun onPermissionDenied()
}