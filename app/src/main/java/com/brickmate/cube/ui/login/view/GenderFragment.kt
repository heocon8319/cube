package com.brickmate.cube.ui.login.view

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.brickmate.cube.BabyGender
import com.brickmate.cube.R
import com.brickmate.cube.sharedPrefs
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.utils.TAG
import kotlinx.android.synthetic.main.fragment_baby_name.*
import kotlinx.android.synthetic.main.fragment_gender.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenderFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var isBoyMultiTime: Boolean = false
    private var isGirlMultiTime: Boolean = false
    private var isGirl = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun layoutId() = R.layout.fragment_gender

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showArrow()
        initListener()
    }

    private fun initListener() {
        ivBoy.setOnClickListener {
            if (!isBoyMultiTime) {
                isGirl = false
                ivBoy.setImageResource(R.drawable.ic_boy_enable)
                ivGirl.setImageResource(R.drawable.ic_girl_disable)
                updateImageBG(isGirl)
                isBoyMultiTime = true
                isGirlMultiTime = false
            }
        }

        ivGirl.setOnClickListener {
            if (!isGirlMultiTime) {
                isGirl = true
                ivGirl.setImageResource(R.drawable.ic_girl_enable)
                ivBoy.setImageResource(R.drawable.ic_boy_disable)
                updateImageBG(isGirl)
                isBoyMultiTime = false
                isGirlMultiTime = true
            }
        }

    }

    private fun updateImageBG(girl: Boolean) {
        //loading our custom made animations
        val animation = AnimationUtils.loadAnimation(activity, R.anim.anim_gender)
        if (girl)
            ivBGGender.setBackgroundResource(R.drawable.bg_girl_gender)
        else
            ivBGGender.setBackgroundResource(R.drawable.bg_boy_gender)

        //starting the animation
        ivBGGender.startAnimation(animation)
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        getData()
        val fragDes = DayOfBirthFragment.newInstance()
        navigateToFragment(fragDes, fragDes.TAG())
    }

    private fun getData() {
        val gender = if (isGirl) BabyGender.FEMALE else BabyGender.MALE
        sharedPrefs.setBabyGender(gender.toString())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GenderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GenderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): GenderFragment = GenderFragment()
    }
}