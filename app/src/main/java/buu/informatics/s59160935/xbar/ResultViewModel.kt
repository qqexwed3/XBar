package buu.informatics.s59160935.xbar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import buu.informatics.s59160935.xbar.database.Score
import buu.informatics.s59160935.xbar.database.ScoreDatabaseDAO
import kotlinx.coroutines.*

class ResultViewModel(finalscore:Int,  val database: ScoreDatabaseDAO,
                      application: Application
): AndroidViewModel(application) {

    val score = finalscore
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        uiScope.launch {
            storeScore(score)
        }
    }

    private suspend fun storeScore(score: Int) {
        return withContext(Dispatchers.IO) {
            val newScore = Score()
            newScore.score = score
            database.insert(newScore)
//            database.clear()
        }
    }
}
