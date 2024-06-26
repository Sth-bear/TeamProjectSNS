package com.example.teamproject1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityMainBinding

//로그인
class SingInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if (result.resultCode == RESULT_OK) {
                    val getId = result.data?.getStringExtra("id") ?: ""
                    val getPw = result.data?.getStringExtra("pw") ?: ""
                    binding.etId.setText(getId)
                    binding.etPw.setText(getPw)
                }
        }


        binding.btnSighIn.setOnClickListener {
            if(login(id = binding.etId.text.toString(), password = binding.etPw.text.toString())) {
                val intent = Intent(this, LobbyActivity::class.java)
                Global.id = binding.etId.text.toString()
                intent.putExtra("loginId", Global.id)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, getText(R.string.idpw), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSighUp.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    private fun login(id: String, password: String): Boolean {
        for (user in userList) {
            if (user.id == id && user.password == password)
                return true
        }
        return false
    }
}

