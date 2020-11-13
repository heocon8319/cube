package com.brickmate.cube.ui.login

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.brickmate.cube.AppConstants
import com.brickmate.cube.R
import com.brickmate.cube.sharedPrefs
import kotlinx.android.synthetic.main.activity_crop_image.*
import java.io.ByteArrayOutputStream


class CropImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)

        val uri = intent.getStringExtra("UriImage")
        ciCropView.setImageUriAsync(Uri.parse(uri))

        llButton.setOnClickListener {
            val cropped: Bitmap = ciCropView.getCroppedImage(500, 500)
            if (cropped != null) {
                sharedPrefs.setAvatar(encodeToBase64(cropped))
                setResultActivity()
            }
        }

        ivArrowBack.setOnClickListener {
            finish()
        }
    }

    private fun setResultActivity() {
        val intent = Intent()
        intent.putExtra("BitmapImage", "BitmapImage")
        setResult(AppConstants.CROP_IMAGE, intent)
        finish()
    }

    private fun encodeToBase64(image: Bitmap): String? {
        val byte = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, byte)
        val b: ByteArray = byte.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}