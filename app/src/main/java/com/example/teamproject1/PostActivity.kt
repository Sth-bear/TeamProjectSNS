package com.example.teamproject1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.teamproject1.databinding.ActivityMainBinding
import com.example.teamproject1.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //예시 text입니다. 지우시고 사용하세요.
        binding.tvTitle.text = "오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요."
//        binding.tvTitle.text = "오늘 날씨가 엄청 덥네요!"
        setViewMore()
    }

    private fun setViewMore() {
        val tvTitle = binding.tvTitle //게시글
        val tvMore = binding.tvMore //더보기
        tvTitle.post {
            val lineCount = tvTitle.layout.lineCount
            if (tvTitle.layout.getEllipsisCount(lineCount - 1) > 0) {
                // getEllipsisCount는 textView에서 ellipsis를 설정한 경우 생략부호의 수를 반환하는 것입니다.
                // 위에 코드는 ellipsize인지 아닌지 확인하는 코드입니다.
                tvMore.visibility = View.VISIBLE // <더보기 보이기 (짧은 글에는 보일 필요가 없어서 넣었습니다.)
                tvMore.setOnClickListener {
                    if (tvMore.text == "더보기") {
                        tvTitle.maxLines = Int.MAX_VALUE
                        tvMore.text = "접기"
                    } else {
                        tvTitle.maxLines = 2
                        tvMore.text = "더보기"
                    }
                }
            }
        }
    }
}