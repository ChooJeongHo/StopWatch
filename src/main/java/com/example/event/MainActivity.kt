package com.example.event

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var initTime = 0L //시간 초기화
    var pauseTime = 0L //퍼즈시 시간저장

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()+pauseTime
            binding.chronometer.start()
            binding.stopButton.isEnabled=true
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=false
        }

        binding.stopButton.setOnClickListener {
            pauseTime=binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=true
        }

        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=false
            binding.startButton.isEnabled=true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode === KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000){  //3초
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()  //누른시간 저장
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}