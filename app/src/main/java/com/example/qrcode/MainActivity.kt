package com.example.qrcode

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        First.imgView = findViewById<ImageView>(R.id.imageView)
        First.edtext = findViewById<EditText>(R.id.ed1)
        bt1.setOnClickListener {
            getQRcode()
        }
    }

    private fun getQRcode(){
        val encoder = BarcodeEncoder()
        try {
            val bit = encoder.encodeBitmap(
                edtext?.getText()
                    .toString(), BarcodeFormat.QR_CODE, 250, 250
            )
            imgView?.setImageBitmap(bit)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }



    companion object First{
       var imgView : ImageView? = null
        var edtext :EditText? = null
    }
}