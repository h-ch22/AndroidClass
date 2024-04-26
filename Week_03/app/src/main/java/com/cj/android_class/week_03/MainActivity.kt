package com.cj.android_class.week_03

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.cj.android_class.week_03.databinding.LayoutMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var viewBinding: LayoutMainBinding
    private var contentsList = listOf(R.drawable.img_first, R.drawable.img_second, R.drawable.img_third)
    private var doubleTap = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.layout_main)
        viewBinding.view = this

        viewBinding.setVariable(BR.likeList, mutableListOf(0, 0, 0))
        viewBinding.setVariable(BR.index, 0)
        viewBinding.lifecycleOwner = this
    }

    fun onClick(v: View){
        when(v){
            viewBinding.imgContents -> {
                if (doubleTap){
                    updateListElements(1)
                }

                doubleTap = true
                Handler(Looper.getMainLooper()).postDelayed({
                    doubleTap = false
                }, 1500)
            }

            viewBinding.btnLike -> {
                updateListElements(if(viewBinding.likeList!![viewBinding.index!!] == 1) 0 else 1)
            }
        }
    }

    fun changeContents(): Boolean{
        viewBinding.index = if(viewBinding.index!! < contentsList.size - 1) viewBinding.index!! + 1 else 0
        viewBinding.imgContents.setImageDrawable(ResourcesCompat.getDrawable(resources, contentsList[viewBinding.index!!], null))

        return true
    }

    private fun updateListElements(newVal: Int){
        val newList = mutableListOf(0, 0, 0)

        for(i in contentsList.indices){
            if(i != viewBinding.index){
                newList[i] = viewBinding.likeList!![viewBinding.index!!]
            } else{
                newList[i] = newVal
            }
        }

        viewBinding.setVariable(BR.likeList, newList)
    }
}
