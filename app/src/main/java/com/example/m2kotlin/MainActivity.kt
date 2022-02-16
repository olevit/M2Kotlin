package com.example.m2kotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val REQUEST_TAKE_PHOTO = 0
    lateinit var imageFilePath: String
    lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (cameraIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    /*private fun setScaledBitmap(): Bitmap {
        val imageViewWidth = imageView.width
        val imageViewHeight = imageView.height

        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFilePath, bmOptions)
        val bitmapWidth = bmOptions.outWidth
        val bitmapHeight = bmOptions.outHeight

        val scaleFactor = Math.min(bitmapWidth / imageViewWidth, bitmapHeight / imageViewHeight)

        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor


        return BitmapFactory.decodeFile(imageFilePath, bmOptions)
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_TAKE_PHOTO -> {
                // if (resultCode == Activity.RESULT_OK && data !== null) {
                //     // миниатюра
                //     //imageView.setImageBitmap(data.extras.get("data") as Bitmap)
                //
                //     // полноразмерное изображение
                //     //imageView.setImageURI(imageUri)
                // }
                if (resultCode == Activity.RESULT_OK) {
                    // масштабируем изображение
                    val extras = data!!.extras
                    val imageBitmap = extras!!["data"] as Bitmap?
                    imageView.setImageBitmap(imageBitmap)
                }
            }
            else -> {
                Toast.makeText(this, "Wrong request code", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
