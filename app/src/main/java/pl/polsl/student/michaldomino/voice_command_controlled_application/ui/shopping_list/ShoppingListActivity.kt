package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.ShoppingListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.ShoppingListItemsManager


class ShoppingListActivity : AppCompatActivity(), ShoppingListContract.View {

    private val presenter: ShoppingListContract.Presenter = ShoppingListPresenter(this)

    private val mDetector: GestureDetectorCompat =
        GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))

    private val parentLinearLayout: LinearLayout = findViewById(R.id.parent_linear_layout)

    private val shoppingListItemsManager: ShoppingListItemsManager =
        ShoppingListItemsManager(layoutInflater, parentLinearLayout)

    private val speaker: Speaker = Speaker(applicationContext)

    private val commandRecognizer: CommandRecognizer = CommandRecognizer(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        setSupportActionBar(toolbar)

        shoppingListItemsManager.addRow("one")
        shoppingListItemsManager.addRow("two")
        shoppingListItemsManager.addRow("elephant")
        shoppingListItemsManager.addRow("dog")

        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.start()
    }

    override fun startListening() {
        commandRecognizer.startListening()
    }

    override fun onCommandRecognizerResults(bundle: Bundle) {
        presenter.processInput(bundle)
    }

    override fun speakInForeground(message: String) {
        speaker.speakInForeground(message)
    }

    override fun addRow(text: CharSequence) {
        shoppingListItemsManager.addRow(text)
    }

    override fun getItems(): MutableList<ShoppingListItem> {
        return shoppingListItemsManager.items
    }

    override fun setNewItemName(item: ShoppingListItem, newName: String) {
        item.setText(newName)
    }
}
