package com.danielebottillo.blog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<IndicatorView>(R.id.indicator1).setOnClickListener {
            findViewById<IndicatorView>(R.id.indicator1).colors = listOf(Color.BLACK, Color.RED)
        }

        findViewById<IndicatorView>(R.id.indicator1).colors = listOf(Color.BLUE)
        findViewById<IndicatorView>(R.id.indicator2).colors = listOf(Color.BLACK, Color.RED)
        findViewById<IndicatorView>(R.id.indicator3).colors = listOf(Color.WHITE, Color.RED, Color.GREEN)
        findViewById<IndicatorView>(R.id.indicator4).colors = listOf(Color.BLUE, Color.BLACK, Color.RED, Color.GREEN)
        findViewById<IndicatorView>(R.id.indicator5).colors = listOf(Color.WHITE, Color.BLUE, Color.BLACK, Color.RED, Color.GREEN)
    }
}
