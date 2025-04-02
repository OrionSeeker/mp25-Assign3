package com.michael.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.michael.assignment3.model.User

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var nameInput: EditText = findViewById(R.id.nameRegister)
        var emailInput: EditText = findViewById(R.id.emailRegister)
        var passInput: EditText = findViewById(R.id.passwordRegister)
        var passInput2: EditText = findViewById(R.id.passwordRegister2)
        var registBtn: Button = findViewById(R.id.registerBtn)
        var goToLoginBtn: Button = findViewById(R.id.goToLoginBtn)

        registBtn.setOnClickListener{
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val pass = passInput.text.toString().trim()
            val pass2 = passInput2.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || pass2.isEmpty()){
                Toast.makeText(this, "Nama, Email, dan Password Wajib Diisi!!!!!", Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Format email tidak valid!", Toast.LENGTH_SHORT).show()
            }
            else if(!pass.equals(pass2)){
                Toast.makeText(this, "Password tidak sama!", Toast.LENGTH_SHORT).show()
            }
            else{
                val user = User(name, email, pass)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_DATA", user)
                Toast.makeText(this, "Register Berhasil!!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }

        goToLoginBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}