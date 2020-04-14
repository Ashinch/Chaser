package com.ash.chaser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSend.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val conn =
                    URL("https://bapi.chdsh.net/resource/recent?packageTypeId=8").openConnection() as HttpURLConnection
                conn.apply {
                    requestMethod = "GET"
                    doInput = true
                    doOutput = true
                }
                when (conn.responseCode) {
                    200 -> {
                        val result = conn.inputStream.bufferedReader().use { it.readText() }
                        println("result: $result")
                    }
                }
            }
        }
    }
}
