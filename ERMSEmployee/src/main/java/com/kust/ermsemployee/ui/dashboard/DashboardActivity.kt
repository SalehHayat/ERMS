package com.kust.ermsemployee.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kust.ermsemployee.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}