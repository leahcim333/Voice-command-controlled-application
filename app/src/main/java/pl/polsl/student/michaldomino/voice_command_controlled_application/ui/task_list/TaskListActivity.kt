package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.task_list.TaskListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.task_list.TaskListItemsManager


class TaskListActivity : AppCompatActivity(), TaskListContract.View {

    private lateinit var presenter: TaskListContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var taskListItemsManager: TaskListItemsManager

    private lateinit var speaker: Speaker

    private lateinit var commandRecognizer: CommandRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(toolbar)

        presenter = TaskListPresenter(this)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        taskListItemsManager = TaskListItemsManager(layoutInflater, parentLinearLayout)
        speaker = Speaker(applicationContext)
        commandRecognizer = CommandRecognizer(this)

        taskListItemsManager.addRow("one")
        taskListItemsManager.addRow("two")
        taskListItemsManager.addRow("elephant")
        taskListItemsManager.addRow("dog")

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
        taskListItemsManager.addRow(text)
    }

    override fun getItems(): MutableList<TaskListItem> {
        return taskListItemsManager.items
    }

    override fun setNewItemName(item: TaskListItem, newName: String) {
        item.setText(newName)
    }
}
