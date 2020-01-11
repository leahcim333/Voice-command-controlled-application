package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

interface NotePresenter : VoiceCommandsPresenter {
    fun saveChanges()
    fun discardChanges()
    fun closeNote()
}