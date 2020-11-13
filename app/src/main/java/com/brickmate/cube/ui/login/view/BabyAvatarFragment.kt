package com.brickmate.cube.ui.login.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import com.brickmate.cube.AppConstants.Companion.CROP_IMAGE
import com.brickmate.cube.AppConstants.Companion.PICK_IMAGE
import com.brickmate.cube.R
import com.brickmate.cube.sharedPrefs
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.ui.login.CropImageActivity
import com.brickmate.cube.utils.TAG
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_baby_avatar.*


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
            .into(ivBabyAvatar)
    }

    private fun initListener() {
        ivBabyAvatar.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, resources.getString(R.string.screen_crop_text_selected)), PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                val imageUri = data?.data
                imageUri?.let {
                    goToCropActivity(it)
                }
            }
        } else if (requestCode == CROP_IMAGE) {
            val photo = decodeBase64(sharedPrefs.getAvatar())
                Glide.with(this).load(photo)
                    .placeholder(R.drawable.ic_baby_avatar_holder)
                    .error(R.drawable.ic_baby_avatar_holder)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivBabyAvatar)
        }
    }

    private fun goToCropActivity(imageUri: Uri) {
        val intent = Intent(activity, CropImageActivity::class.java)
        intent.putExtra("UriImage", imageUri.toString())
        startActivityForResult(intent, CROP_IMAGE)
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        val fragDes = GoodIngredientsFragment.newInstance()
        navigateToFragment(fragDes, fragDes.TAG())
    }

    private fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }


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