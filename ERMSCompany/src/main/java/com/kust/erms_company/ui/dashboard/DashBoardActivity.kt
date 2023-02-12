package com.kust.erms_company.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kust.erms_company.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}