package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.note_selection.NoteType

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    @NonNull
    var id: Long,

    @ColumnInfo(name = "note_name")
    @NonNull
    var name: String,

    @ColumnInfo(name = "note_type")
    @NonNull
    var type: NoteType
) {
    constructor(noteName: String, type: NoteType) : this(0, noteName, type)
}