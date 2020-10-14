package com.example.qrcode

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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

        val packageManager = packageManager
        val activities: List<*> = packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        val isIntentSafe = activities.isNotEmpty()

        bt1.setOnClickListener {
            if(bt1.text == "產生"){
                if(isIntentSafe){
                    getQRcode("https://www.google.com/?client=safari")
                    //gotoWeb("https://www.google.com/?client=safari")
                    bt1.text = "清除"
                }else{
                    print("沒有應用程式可以開啟")
                }
            }else{
                bt1.text = "產生"
                imageView.setImageBitmap(null)
                ed1.setText("")
            }
        }


    }

    private fun getQRcode(url: String){
        val encoder = BarcodeEncoder()
        try {
            val bit = encoder.encodeBitmap(
                url, BarcodeFormat.QR_CODE, 250, 250
            )
            imgView?.setImageBitmap(bit)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    private fun gotoWeb(url: String){
        //getQRcode(url)

        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

    }



    companion object First{
         var imgView : ImageView? = null
        var edtext :EditText? = null
    }
}