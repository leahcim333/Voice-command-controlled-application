package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

class InitialCS(override val presenter: BasePresenter) : BaseCommandState(presenter) {
    override val responseMap: Map<Int, Unit>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
//    override var responseMap: Map<Int, Unit>

    override fun setState(newState: BaseCommandState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
//
//    private val map : Map<String, BaseCommandState> = mapOf(
//        R.string.add_element to
//    )

    override fun performCommand(command: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}