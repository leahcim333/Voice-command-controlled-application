package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteType

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    @NonNull
    val id: Long,

    @ColumnInfo(name = "note_name")
    @NonNull
    var name: String,

    @ColumnInfo(name = "note_name")
    @NonNull
    var type: NoteType
) {
    constructor(noteName: String, type: NoteType) : this(0, noteName, type)
}