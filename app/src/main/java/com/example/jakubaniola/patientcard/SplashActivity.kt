package com.example.jakubaniola.patientcard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({ openActivity() }, 1000)
    }

    private fun openActivity() {
        val activityToOpen = Intent(this, QRReaderActivity::class.java)
        startActivity(activityToOpen)
    }
}
