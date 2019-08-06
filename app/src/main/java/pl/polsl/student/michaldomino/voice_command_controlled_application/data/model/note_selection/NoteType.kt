package pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection

enum class NoteType(val resId: Int) {
    SHOPPING_LIST(android.R.drawable.checkbox_on_background),
    NOTE(android.R.drawable.ic_menu_edit)
}