package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base

abstract class BaseCommandsModel {

    abstract val availableCommands: MutableList<Int>
}