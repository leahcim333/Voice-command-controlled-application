package pl.polsl.student.michaldomino.voice_command_controlled_application.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteType

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val noteId: Long,

    @NotNull
    val noteName: String,

    @NotNull
    val noteType: NoteType
) {
    constructor(noteName: String, type: NoteType) : this(0, noteName, type)
}