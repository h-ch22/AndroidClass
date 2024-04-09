package com.cj.android_class.month_01

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cj.android_class.month_01.databinding.LayoutMainBinding
import java.text.DecimalFormat
import kotlin.concurrent.thread
import kotlin.system.measureNanoTime
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.measureTime
import kotlin.time.measureTimedValue
import kotlin.time.toDuration

class MainActivity: AppCompatActivity() {
    private lateinit var view: LayoutMainBinding
    private var range: Int? = null
    private var result: ULong? = 0u
    private var elapsedTime = Duration.ZERO
    private var selectedType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.layout_main)
        view.view = this
    }

    private fun solA(): ULong{
        var result: ULong = 0u

        return result
    }

    private fun solB(): ULong{
        var result: ULong = 0u

        return result
    }

    private fun solC(): ULong{
        var result: ULong = 0u

        return result
    }

    fun onClick(v: View){
        if(view.fieldRange.text.toString() == ""){
            Toast.makeText(this, "범위를 입력하십시오.", Toast.LENGTH_SHORT).show()
        } else{
            range = view.fieldRange.text.toString().toInt()

            if(range!! < 0 || range!! > 93){
                Toast.makeText(this, "0~93 사이의 정수를 입력하십시오.", Toast.LENGTH_SHORT).show()
            } else{
                view.progressView.visibility = View.VISIBLE

                thread(true){
                    elapsedTime = measureTime {
                        when(v){
                            view.btnSolA -> {
                                selectedType = "Solution A"
                                result = solA()
                            }

                            view.btnSolB -> {
                                selectedType = "Solution B"
                                result = solB()
                            }

                            view.btnSolC -> {
                                selectedType = "Solution C"
                                result = solC()
                            }
                        }
                    }

                    runOnUiThread {
                        view.progressView.visibility = View.GONE

                        view.txtResult.text = result!!.toString()
                        view.txtElapsedTime.text =
                            "elapsed Time: ${ elapsedTime.inWholeSeconds }s (${selectedType})"

                        view.txtResult.visibility = View.VISIBLE
                        view.txtElapsedTime.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}