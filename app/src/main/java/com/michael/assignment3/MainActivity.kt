package com.michael.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.michael.assignment3.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emailInput: EditText = findViewById(R.id.emailLogin)
        var passInput: EditText = findViewById(R.id.passwordLogin)
        var loginBtn: Button = findViewById(R.id.loginBtn)
        var registBtn: Button = findViewById(R.id.goToRegisterBtn)

        var dataUser: User? = intent.getParcelableExtra("USER_DATA")

        loginBtn.setOnClickListener{
            val email = emailInput.text.toString().trim()
            val pass = passInput.text.toString().trim()

            if(dataUser == null){
                Toast.makeText(this, "Tidak ada user!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(email.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Email dan Password wajib diisi!!!", Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Format email tidak valid!", Toast.LENGTH_SHORT).show()
            }
            else{
                if (email == dataUser.email && pass == dataUser.password) {
                    Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Dashboard::class.java)
                    intent.putExtra("USER_DATA", dataUser)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Email atau password salah!!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registBtn.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
            finish()
        }
    }
}