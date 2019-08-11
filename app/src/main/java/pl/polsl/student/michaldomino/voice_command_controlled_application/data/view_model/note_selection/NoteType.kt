package pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.note_selection

enum class NoteType(val typeId: Int, val resId: Int) {
    TASK_LIST(0, android.R.drawable.checkbox_on_background),
    TEXT_NOTE(1, android.R.drawable.ic_menu_edit)
}