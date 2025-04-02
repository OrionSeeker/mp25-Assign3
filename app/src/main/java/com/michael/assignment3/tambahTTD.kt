package com.michael.assignment3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.michael.assignment3.model.TandaTangan
import com.michael.assignment3.model.User

class tambahTTD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_ttd)

        var penerimaTTD: EditText = findViewById(R.id.penerimaTTD)
        var perihalTTD: EditText = findViewById(R.id.perihalTTD)
        var tambahTTDBtn: Button = findViewById(R.id.tambahTTDBtn)

        tambahTTDBtn.setOnClickListener{
            val penerima = penerimaTTD.text.toString().trim()
            val perihal = perihalTTD.text.toString().trim()

            if(penerima.isEmpty() || perihal.isEmpty()){
                Toast.makeText(this, "Penerima dan Perihal Wajib Diisi!!!!!", Toast.LENGTH_SHORT).show()
            }
            else{
                val daftarTTDList: ArrayList<TandaTangan> = intent.getParcelableArrayListExtra("TTD_DATA") ?: arrayListOf()
                daftarTTDList.add(TandaTangan(arrayListOf(penerima), arrayListOf(perihal)))

                val dataUser: User? = intent.getParcelableExtra("USER_DATA")
                val intent = Intent(this, Dashboard::class.java)
                intent.putParcelableArrayListExtra("TTD_DATA", daftarTTDList)
                intent.putExtra("USER_DATA", dataUser)
                startActivity(intent)
                finish()

            }
        }

    }
}