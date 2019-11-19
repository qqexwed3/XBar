package buu.informatics.s59160935.xbar

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _score = MutableLiveData<Int>()
    var questionIndex = 0
    val score: LiveData<Int>
        get() = _score

    private val _life = MutableLiveData<Int>()
    val life: LiveData<Int>
        get() = _life

    val answerListNo1 = arrayOf(
        arrayOf(1,1),
        arrayOf(2,2),
        arrayOf(3,3),
        arrayOf(8,4),
        arrayOf(3,7))
    val answerListNo2 = arrayOf(
        arrayOf(5,5),
        arrayOf(4,4),
        arrayOf(9,3),
        arrayOf(1,4),
        arrayOf(8,8))

    private val _answerNo11 = MutableLiveData<Int>()
    val answerNo11: LiveData<Int>
        get() = _answerNo11

    private val _answerNo12 = MutableLiveData<Int>()
    val answerNo12: LiveData<Int>
        get() = _answerNo12

    private val _answerNo21 = MutableLiveData<Int>()
    val answerNo21: LiveData<Int>
        get() = _answerNo21

    private val _answerNo22 = MutableLiveData<Int>()
    val answerNo22: LiveData<Int>
        get() = _answerNo22

    private val _eventEndGame = MutableLiveData<Boolean>()
    val eventEndGame: LiveData<Boolean>
        get() = _eventEndGame

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        //        private const val COUNTDOWN_TIME = 30000L
        private const val COUNTDOWN_TIME = 20000L
    }
    private val timer: CountDownTimer

    init {
        _score.value  = 0
        _answerNo11.value = 0
        _answerNo12.value = 0
        _answerNo21.value = 0
        _answerNo22.value = 0
        _eventEndGame.value = false

        _life.value = 3
        nextQuestion()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ONE_SECOND
            }
            override fun onFinish() {
                _currentTime.value = DONE
                onEndGame()
            }
        }
        timer.start()
    }
    val currentScoreString = Transformations.map(score) { score ->
        "SCORE : ${score}"
    }
    val currentLifeString = Transformations.map(life) { life ->
        "LIFE : ${life}"
    }
    val s1 = Transformations.map(answerNo11) { no1 ->
        Transformations.map(answerNo12) { no2 ->
            "${no1} + ${no2}"
        }
    }

    val s2 = Transformations.map(answerNo21) { no1 ->
        Transformations.map(answerNo22) { no2 ->
            "${no1} + ${no2}"
        }
    }

    val currentTimerString = Transformations.map(currentTime) { time ->
        "TIME : ${DateUtils.formatElapsedTime(time)}"
    }


    fun onEndGame(){
        _eventEndGame.value = true
    }

    fun onCheckAnswer(value : Int){

        val checkNo1 = _answerNo11.value?.plus(_answerNo12.value!!)?.div(2)!!.toDouble()
        val checkNo2 = _answerNo21.value?.plus(_answerNo22.value!!)?.div(2)!!.toDouble()

        if(value == 1){
            if(checkNo1 < checkNo2){
                correct()
                nextQuestion()
            }
            else{
                incorrect()
                nextQuestion()
            }
        }else{
            if(checkNo2 < checkNo1){
                correct()
                nextQuestion()
            }
            else{
                incorrect()
                nextQuestion()
            }
        }

    }
    private fun correct(){
        _score.value = (_score.value)?.plus(10)
    }
    private fun incorrect(){
        _life.value = (_life.value)?.minus(1)

        if(_life.value?.equals(0)!!){
            onEndGame()
        }

    }
    private fun nextQuestion() {
        if(questionIndex == 5){
            onEndGame()
        }else{

            _answerNo11.value = answerListNo1[questionIndex][0]
            _answerNo12.value = answerListNo1[questionIndex][1]

            _answerNo21.value = answerListNo2[questionIndex][0]
            _answerNo22.value = answerListNo2[questionIndex][1]


        }
        questionIndex +=1
    }

}
