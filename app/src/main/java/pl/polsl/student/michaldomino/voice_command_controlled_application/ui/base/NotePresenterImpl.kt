package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

abstract class NotePresenterImpl(override val view: VoiceCommandsView) :
    VoiceCommandsPresenterImpl(view), NotePresenter {

    override fun closeNote() {
        view.finish()
    }
}