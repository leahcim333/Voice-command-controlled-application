package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TextNote(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "text_note_id")
    @NonNull
    val id: Long,

    @ColumnInfo(name = "text")
    @NonNull
    var text: String
) {
    constructor(text: String) : this(0, text)
}