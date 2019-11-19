package buu.informatics.s59160935.xbar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import buu.informatics.s59160935.xbar.database.ScoreDatabaseDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HistoryViewModel (val database: ScoreDatabaseDAO,
                           application: Application
): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var scoreList = database.getScoreBoard()
}
