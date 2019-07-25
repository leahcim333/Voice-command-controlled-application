package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSDynamicNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

class EditShoppingListElementCS(override val presenter: BasePresenter) : CSDynamicNode(presenter) {
    override val messageToSpeak: String = presenter.getString(R.string.edit_item) // temp
    override val commandName: String? = presenter.getString(R.string.edit_item)

    override fun processInput(userInput: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}