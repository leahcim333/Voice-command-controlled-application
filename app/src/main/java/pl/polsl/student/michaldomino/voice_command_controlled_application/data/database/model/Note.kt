package pl.polsl.student.michaldomino.voice_command_controlled_application.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.note_selection.NoteType

@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val noteId: Int,

    val noteName: String,

    val type: NoteType


)

class NoteTypeConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun noteTypeToInt(noteType: NoteType): Int = noteType.typeId

        @TypeConverter
        @JvmStatic
        fun intToNoteType(typeId: Int): NoteType {
            return NoteType.values().find { it.typeId == typeId }!!
        }
    }

}