package com.jindoblu.voicenote.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jindoblu.voicenote.data.model.room.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): Flow<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg noteModel: NoteModel)

    @Query("DELETE FROM note WHERE id = :userId")
    fun deleteNotesById(userId: Long)

    @Delete
    fun delete(user: NoteModel)
}