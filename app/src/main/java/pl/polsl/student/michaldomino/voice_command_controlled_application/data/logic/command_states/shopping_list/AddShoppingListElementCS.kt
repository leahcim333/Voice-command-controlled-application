package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class AddShoppingListElementCS(override val presenter: ShoppingListPresenter) : CSLeaf(presenter) {

    init {
        presenter.initializeAddingElements()
    }
}
