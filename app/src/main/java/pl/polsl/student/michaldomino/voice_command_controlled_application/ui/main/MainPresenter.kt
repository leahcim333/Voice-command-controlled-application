package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListActivity


class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun start() {
        view.startActivityFromClass(TaskListActivity::class.java)
        view.finish()
    }

    override fun doSth(notes: List<Note>?) {
        val f = 0
    }

    override fun doElse(error: Throwable?) {
        val f = 0
    }
}