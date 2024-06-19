package com.cj.week_05.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cj.week_05.R
import com.cj.week_05.databinding.LayoutStartBinding

class StartActivity: AppCompatActivity() {
    private lateinit var view: LayoutStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.layout_start)
        view.view = this
        view.lifecycleOwner = this

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(view.frameLayout.id, SignInView())
        transaction.commit()
    }
}