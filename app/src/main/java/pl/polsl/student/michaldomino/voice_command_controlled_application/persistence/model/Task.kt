package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model

import androidx.annotation.NonNull
import androidx.room.*

@Entity(
    tableName = "tasks",
    indices = arrayOf(Index(value = arrayOf("note_id"), unique = true)),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Note::class,
            parentColumns = arrayOf("note_id"),
            childColumns = arrayOf("note_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    )
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    @NonNull
    var id: Long,

    @ColumnInfo(name = "task_name")
    @NonNull
    var name: String,

    @ColumnInfo(name = "is_checked")
    @NonNull
    var isChecked: Boolean,

    @ColumnInfo(name = "note_id")
    @NonNull
    val noteId: Long
) {
    constructor(taskName: String, isChecked: Boolean, noteId: Long) :
            this(0, taskName, isChecked, noteId)

    constructor(taskName: String, noteId: Long) :
            this(0, taskName, false, noteId)
}