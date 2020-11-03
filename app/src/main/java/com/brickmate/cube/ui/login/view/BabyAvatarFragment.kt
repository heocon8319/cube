package com.brickmate.cube.ui.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.toast
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
            .into(ivBabyAvatar);
    }

    private fun initListener() {
        ivBabyAvatar.setOnClickListener {
            toast(getString(R.string.developing_text))
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