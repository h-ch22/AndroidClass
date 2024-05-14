package com.cj.week_05.ui

import android.app.Application
import com.google.android.material.color.DynamicColors

class Week_05: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}