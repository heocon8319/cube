package com.brickmate.cube.ui.login.view

import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import com.brickmate.cube.AppConstants.Companion.CROP_IMAGE
import com.brickmate.cube.AppConstants.Companion.PICK_IMAGE
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_baby_avatar.*
import java.io.FileNotFoundException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BabyAvatarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BabyAvatarFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    /**
     * Persist URI image to crop URI if specific permissions are required
     */
    private var mCropImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun layoutId() = R.layout.fragment_baby_avatar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        showArrow()
        Glide.with(this).load(R.drawable.img_baby)
            .placeholder(R.drawable.ic_baby_avatar_holder)
            .error(R.drawable.ic_baby_avatar_holder).apply(RequestOptions.circleCropTransform())
            .into(ivBabyAvatar);
    }

    private fun initListener() {
        ivBabyAvatar.setOnClickListener {
            toast(getString(R.string.developing_text))
            val i = Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, PICK_IMAGE)
//            val photoPickerIntent = Intent(Intent.ACTION_PICK)
//            photoPickerIntent.type = "image/*"
//            startActivityForResult(photoPickerIntent, PICK_IMAGE)
//            onSelectImageClick()
        }
    }

    /**
     * Start pick image activity with chooser.
     */
    private fun onSelectImageClick() {
        CropImage.startPickImageActivity(context as Activity)
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        val fragDes = GoodIngredientsFragment.newInstance()
        navigateToFragment(fragDes, fragDes.TAG())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === PICK_IMAGE && resultCode === RESULT_OK && null != attr.data) {
            try {
//                val imageUri: Uri? = data?.data
//                val imageStream: InputStream? =
//                    imageUri?.let { activity.contentResolver.openInputStream(it) }
//                val selectedImage = BitmapFactory.decodeStream(imageStream)
//                Glide.with(this).load(selectedImage)
//                    .placeholder(R.drawable.ic_baby_avatar_holder)
//                    .error(R.drawable.ic_baby_avatar_holder)
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(ivBabyAvatar);

                val imageUri: Uri? = data?.data
                imageUri?.let { performCrop(it) }
//                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//                val cursor: Cursor? = imageUri?.let {
//                    activity.contentResolver.query(
//                        it,
//                        filePathColumn, null, null, null
//                    )
//                }
//                cursor?.moveToFirst()
//                val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
//                val picturePath: String = cursor.getString(columnIndex)
//                cursor?.close()
//                val selectedImage = BitmapFactory.decodeFile(picturePath)
//                Glide.with(this).load(selectedImage)
//                    .placeholder(R.drawable.ic_baby_avatar_holder)
//                    .error(R.drawable.ic_baby_avatar_holder)
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(ivBabyAvatar);

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                toast("Something went wrong")
            }
        } else if (requestCode === CROP_IMAGE && resultCode === RESULT_OK && null != attr.data) {
            if (attr.data != null) {
                // get the returned data
                val extras: Bundle = data?.extras!!
                // get the cropped bitmap
                val selectedBitmap = extras.getParcelable<Bitmap>("data")
                Glide.with(this).load(selectedBitmap)
                    .placeholder(R.drawable.ic_baby_avatar_holder)
                    .error(R.drawable.ic_baby_avatar_holder)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivBabyAvatar);
            }
        } else {
            toast("You haven't picked Image")
        }
    }

    private fun performCrop(picUri: Uri) {
        try {
            val cropIntent = Intent("com.android.camera.action.CROP")
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*")
            // set crop properties here
            cropIntent.putExtra("crop", true)
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1)
            cropIntent.putExtra("aspectY", 1)
            // indicate output X and Y
            cropIntent.putExtra("outputX", 128)
            cropIntent.putExtra("outputY", 128)
            // retrieve data on return
            cropIntent.putExtra("return-data", true)
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, CROP_IMAGE)
        } // respond to users whose devices do not support the crop action
        catch (anfe: ActivityNotFoundException) {
            // display an error message
            toast("Whoops - your device doesn't support the crop action!")
        }
    }

//        // handle result of pick image chooser
//        if (requestCode === CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode === RESULT_OK && data != null) {
//            val imageUri = context?.let { CropImage.getPickImageResultUri(it, data) }
////            val imageUri: Uri = data!!.data!!
//            // For API >= 23 we need to check specifically that we have permissions to read external storage.
//            if (imageUri?.let {
//                    context?.let { it1 ->
//                        CropImage.isReadExternalStoragePermissionsRequired(
//                            it1, it
//                        )
//                    }
//                }!!) {
//                // request permissions and handle the result in onRequestPermissionsResult()
//                mCropImageUri = imageUri
//                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
//            } else {
//                // no permissions required or already grunted, can start crop image activity
//                startCropImageActivity(imageUri)
//            }
//        } else {
//            toast("You haven't picked Image")
//        }
//
//        // handle result of CropImageActivity
//        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            val result = CropImage.getActivityResult(data)
//            if (resultCode === RESULT_OK) {
//                val imageUri = result.uri
//                val imageStream: InputStream? = activity.contentResolver.openInputStream(imageUri)
//                val selectedImage = BitmapFactory.decodeStream(imageStream)
//                Glide.with(this).load(selectedImage)
//                    .placeholder(R.drawable.ic_baby_avatar_holder)
//                    .error(R.drawable.ic_baby_avatar_holder)
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(ivBabyAvatar);
//            } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                toast("Cropping failed")
//            }
//        }
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (mCropImageUri != null && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // required permissions granted, start crop image activity
//            startCropImageActivity(mCropImageUri!!)
//        } else {
//            toast("Cancelling, required permissions are not granted")
//        }
//    }
//
//    /**
//     * Start crop image activity for the given image.
//     */
//    private fun startCropImageActivity(imageUri: Uri) {
//        CropImage.activity(imageUri)
//            .setGuidelines(CropImageView.Guidelines.ON)
//            .setMultiTouchEnabled(true)
//            .start(context as Activity)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BabyAvatarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BabyAvatarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): BabyAvatarFragment = BabyAvatarFragment()
    }
}