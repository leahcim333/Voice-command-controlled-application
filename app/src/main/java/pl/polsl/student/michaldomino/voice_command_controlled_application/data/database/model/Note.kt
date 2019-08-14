package pl.polsl.student.michaldomino.voice_command_controlled_application.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val noteId: Int,

    val noteName: String,

    val type: Int
)