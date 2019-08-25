package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    @NonNull
    val taskId: Long,

    @ColumnInfo(name = "task_name")
    @NonNull
    var taskName: String,

    @ColumnInfo(name = "is_checked")
    @NonNull
    var isChecked: Boolean
) {
    constructor(taskName: String, isChecked: Boolean) : this(0, taskName, isChecked)
}