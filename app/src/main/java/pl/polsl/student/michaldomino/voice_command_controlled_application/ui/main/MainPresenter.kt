package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.InitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListActivity


class MainPresenter(override val view: MainContract.View) : MainContract.Presenter(view) {
    override val initialState: CSRoot
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun askForInput(messageId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var currentState: BaseCommandState = InitialCS(this)

    override fun start() {
        view.startActivityFromClass(ShoppingListActivity::class.java)
        view.finish()
    }
}