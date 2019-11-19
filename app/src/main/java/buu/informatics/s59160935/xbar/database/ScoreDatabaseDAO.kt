package buu.informatics.s59160935.xbar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDatabaseDAO {
    @Insert
    fun insert(score: Score)

    @Query("SELECT * FROM score_table ORDER BY scoreId DESC")
    fun getAllScore(): LiveData<List<Score>>

    @Query("SELECT * FROM score_table ORDER BY score_game DESC")
    fun getScoreBoard(): LiveData<List<Score>>

    @Query("DELETE FROM score_table")
    fun clear()
}