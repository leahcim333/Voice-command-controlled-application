package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

class ShoppingListPresenter(private val view: ShoppingListContract.View) : ShoppingListContract.Presenter {

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getString(resId: Int): String {
        return view.getString(resId)
    }
}