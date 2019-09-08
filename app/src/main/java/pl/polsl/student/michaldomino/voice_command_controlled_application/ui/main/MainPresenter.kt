package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListActivity


class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun start() {
        view.startActivityFromClass(TaskListActivity::class.java)
        view.finish()
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}