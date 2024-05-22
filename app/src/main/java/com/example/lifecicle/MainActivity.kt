package com.example.lifecicle

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var mediaPLayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<MaterialButton>(R.id.btnCheck).setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
        }

        Log.i("LifeCycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        mediaPLayer = MediaPlayer.create(this, R.raw.ai_2)
        mediaPLayer?.start()
        Log.i("LifeCycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        mediaPLayer?.seekTo(position)
        mediaPLayer?.start()
        Log.i("LifeCycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        mediaPLayer?.pause()
        if (mediaPLayer != null) {
            position = mediaPLayer!!.currentPosition
        }
        Log.i("LifeCycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        mediaPLayer?.stop()
        mediaPLayer?.release()
        mediaPLayer = null
        Log.i("LifeCycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "onDestroy")
    }
}