package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.content.Intent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.ShoppingListInitialCS

class ShoppingListPresenter(private val view: ShoppingListContract.View) : ShoppingListContract.Presenter {

    override var currentState: BaseCommandState = ShoppingListInitialCS(this)

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDoubleTap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun runCommand(data: Intent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getString(resId: Int): String {
        return view.getString(resId)
    }
}