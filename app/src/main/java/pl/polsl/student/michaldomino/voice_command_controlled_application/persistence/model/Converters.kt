package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model

import androidx.room.TypeConverter
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteType

class Converters {

    @TypeConverter
    fun noteTypeToInt(value: NoteType): Int {
        return value.typeId
    }

    @TypeConverter
    fun intToNoteType(typeId: Int): NoteType {
        return NoteType.values().find { it.typeId == typeId }!!
    }
}