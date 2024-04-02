package com.example.teamproject1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject1.databinding.ActivityDetailBinding

//타인 디테일 페이지
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //animation open
        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, R.anim.slide_right_enter, R.anim.slide_none)
    }

    override fun finish() {
        super.finish()
        //animation close
        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_CLOSE, R.anim.slide_none, R.anim.slide_right_exit)
    }
}