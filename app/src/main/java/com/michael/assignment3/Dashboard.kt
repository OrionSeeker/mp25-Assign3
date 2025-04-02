package com.michael.assignment3

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.michael.assignment3.model.TandaTangan
import com.michael.assignment3.model.User

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val sambutanHi: TextView = findViewById(R.id.hiDashboard)
        val dataUser: User? = intent.getParcelableExtra("USER_DATA")
        if (dataUser != null){
            sambutanHi.text = "Hi, ${dataUser.name}!"
        }

        val daftarTTDList: ArrayList<TandaTangan> = intent.getParcelableArrayListExtra("TTD_DATA") ?: arrayListOf()

        val daftarTTDLayout: LinearLayout = findViewById(R.id.daftarTTDContainer)
        daftarTTDLayout.removeAllViews()

        if (daftarTTDList.isNotEmpty()){
            for (ttd in daftarTTDList){
                for (i in ttd.penerima.indices){
                    val itemLayout = LinearLayout(this)
                    itemLayout.orientation = LinearLayout.HORIZONTAL
                    itemLayout.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    val ttdText = TextView(this)
                    ttdText.text = "Penerima: ${ttd.penerima[i]}\nPerihal: ${ttd.perihal[i]}"
                    ttdText.layoutParams = LinearLayout.LayoutParams(
                        0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f
                    )

                    val lihatButton = Button(this)
                    lihatButton.text = "Lihat"
                    lihatButton.setOnClickListener{
                        lihatTTDQR(R.drawable.qr_code, R.drawable.tanda_tangan)
                    }

                    val buttonLayout = LinearLayout(this)
                    buttonLayout.orientation = LinearLayout.VERTICAL
                    buttonLayout.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    buttonLayout.gravity = android.view.Gravity.CENTER_VERTICAL

                    buttonLayout.addView(lihatButton)

                    itemLayout.addView(ttdText)
                    itemLayout.addView(buttonLayout)

                    daftarTTDLayout.addView(itemLayout)
                }
            }
        }
        else{
            val emptyText = TextView(this)
            emptyText.text = "Belum ada tanda tangan."
            daftarTTDLayout.addView(emptyText)
        }

        val tambahTTDBtn: FloatingActionButton = findViewById(R.id.goToTambahTTDBtn)
        tambahTTDBtn.setOnClickListener{
            val intent = Intent(this, tambahTTD::class.java)
            intent.putParcelableArrayListExtra("TTD_DATA", daftarTTDList)
            intent.putExtra("USER_DATA", dataUser)
            startActivity(intent)
        }
    }

    private fun lihatTTDQR(qrImageRes: Int, ttdImageRes: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Tanda Tangan dan QR Code")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.HORIZONTAL
        layout.setPadding(50, 30, 50, 30)

        val qrImage = ImageView(this)
        qrImage.setImageResource(qrImageRes)
        qrImage.layoutParams = ViewGroup.LayoutParams(400, 400)

        val ttdImage = ImageView(this)
        ttdImage.setImageResource(ttdImageRes)
        ttdImage.layoutParams = ViewGroup.LayoutParams(400, 400)
        ttdImage.setPadding(20, 0, 0, 0)

        layout.addView(qrImage)
        layout.addView(ttdImage)

        dialogBuilder.setView(layout)
        dialogBuilder.setPositiveButton("Tutup") { dialog, _ -> dialog.dismiss() }
        dialogBuilder.create().show()
    }
}
