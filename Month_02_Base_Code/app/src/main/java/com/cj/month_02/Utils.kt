package com.cj.month_02

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData

class Utils {
    companion object{
        var log = mutableStateOf("")

        fun printLog(plateNumber: Int, start: String, to: String){
            log.value += "Move plate ${plateNumber} from ${start} to ${to}\n"
        }

        fun solA(numberOfPlate: Int, start: String = "A", to: String = "C", via: String = "B"){

        }
    }
}