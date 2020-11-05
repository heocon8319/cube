package com.brickmate.cube.ui.login.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.ui.custom.cropimage.Crop
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_baby_avatar.*
import java.io.File
import java.io.InputStream


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
            Crop.pickImage(activity, this, Crop.REQUEST_PICK);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            if (data != null) {
                beginCrop(data.data)
            }
        } else if (requestCode == Crop.REQUEST_CROP)
            if(data != null) {
                handleCrop(resultCode, data)
            }
    }


    private fun beginCrop(source: Uri?) {
        val destination = Uri.fromFile(File(activity.cacheDir, "cropped"))
        Crop.of(source, destination).asSquare().start(activity, this, Crop.REQUEST_CROP)
    }

    private fun handleCrop(resultCode: Int, result: Intent) {
        if (resultCode == RESULT_OK) {
            val imageUri: Uri = Crop.getOutput(result)
            val imageStream: InputStream? = activity.contentResolver.openInputStream(imageUri)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            Glide.with(this).load(selectedImage)
                .placeholder(R.drawable.ic_baby_avatar_holder)
                .error(R.drawable.ic_baby_avatar_holder)
                .apply(RequestOptions.circleCropTransform())
                .into(ivBabyAvatar);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Crop.getError(result).message?.let { toast(it) }
        }
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        val fragDes = GoodIngredientsFragment.newInstance()
        navigateToFragment(fragDes, fragDes.TAG())
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