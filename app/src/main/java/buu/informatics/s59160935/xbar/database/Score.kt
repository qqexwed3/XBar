package buu.informatics.s59160935.xbar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class Score (
    @PrimaryKey(autoGenerate = true)
    var scoreId: Long = 0L,
    @ColumnInfo(name = "time_game")
    var date: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "score_game")
    var score: Int = 0
)