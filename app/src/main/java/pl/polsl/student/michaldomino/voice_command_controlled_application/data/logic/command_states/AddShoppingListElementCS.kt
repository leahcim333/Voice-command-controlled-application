package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter
import java.util.*

class AddShoppingListElementCS(override val presenter: ShoppingListPresenter) : BaseCommandState(presenter) {

    init {
        presenter.initializeAddingElements()
    }

    override fun performCommand(possibleMatches: ArrayList<String>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
