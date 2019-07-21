package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

abstract class BaseCommandState {
    abstract fun getState(command: String?)
}