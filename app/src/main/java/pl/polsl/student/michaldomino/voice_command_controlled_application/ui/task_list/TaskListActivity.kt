package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.DoubleTapListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItemsManager


class TaskListActivity : AppCompatActivity(), TaskListContract.View {

    private lateinit var presenter: TaskListContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var taskListItemsManager: TaskListItemsManager

    private lateinit var speaker: Speaker

    private lateinit var commandRecognizer: CommandRecognizer

    companion object {
        const val PERMISSIONS_REQUEST_RECORD_AUDIO = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(toolbar)

        val noteId = intent.getStringExtra("noteId").toLong()
        val noteName = intent.getStringExtra("noteName")
        title = noteName

        presenter = TaskListPresenter(this, noteId)
        mDetector = GestureDetectorCompat(this, DoubleTapListener(this))
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        taskListItemsManager = TaskListItemsManager(layoutInflater, parentLinearLayout)
        commandRecognizer = CommandRecognizer(this)
        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        speaker = Speaker(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onSpeakerReady() {
        presenter.create()
    }

    override fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            PERMISSIONS_REQUEST_RECORD_AUDIO
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_RECORD_AUDIO -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    presenter.onPermissionGranted()
                } else {
                    presenter.onPermissionDenied()
                }
                return
            }
        }
    }

    override fun startListening() {
        commandRecognizer.startListening()
    }

    override fun onCommandRecognizerResults(bundle: Bundle) {
        presenter.processInput(bundle)
    }

    override fun onDoubleTap() {
        presenter.onDoubleTap()
    }

    override fun speakInForeground(message: String) {
        speaker.speakInForeground(message)
    }

    override fun addTask(task: Task) {
        taskListItemsManager.addTask(task)
    }

    override fun deleteTask(taskListItem: TaskListItem) {
        taskListItemsManager.deleteTaskListItem(taskListItem)
    }

    override fun clearList() {
        taskListItemsManager.clear()
    }

    override fun getItems(): List<TaskListItem> {
        return taskListItemsManager.items
    }

    override fun setNewItemName(item: TaskListItem, newName: String) {
        item.setName(newName)
    }

    override fun onSpeechRecognizerServerError() {
        presenter.handleServerError()
    }
}
