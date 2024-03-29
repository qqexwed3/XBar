package buu.informatics.s59160935.xbar

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160935.xbar.database.ScoreDatabaseDAO

class ResultViewModelFactory(private val finalScore:Int, private val dataSource: ScoreDatabaseDAO, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(finalScore, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}