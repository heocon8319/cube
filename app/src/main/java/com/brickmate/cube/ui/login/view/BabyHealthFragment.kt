package com.brickmate.cube.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_baby_health.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BabyHealthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BabyHealthFragment : BaseFragment() {
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

    override fun layoutId() = R.layout.fragment_baby_health

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
        initData()
    }

    private fun initView() {
        showArrow()
        showBackground()
    }

    private fun initData() {
    }

    private fun initListener() {
        var isStomachSelected: Boolean = false
        var isThermometerSelected: Boolean = false
        var isToiletSelected: Boolean = false

        llStomach.setOnClickListener {
            isStomachSelected = !isStomachSelected
            setLayoutButton(isStomachSelected, llStomach)
        }
        llThermometer.setOnClickListener {
            isThermometerSelected = !isThermometerSelected
            setLayoutButton(isThermometerSelected, llThermometer)
        }
        llToilet.setOnClickListener {
            isToiletSelected = !isToiletSelected
            setLayoutButton(isToiletSelected, llToilet)
        }
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity.finish()
    }

    private fun setLayoutButton(isSelected: Boolean, layout: LinearLayout) {
        if (!isSelected) {
            layout.setBackgroundResource(R.drawable.bg_white)
        } else {
            layout.setBackgroundResource(R.drawable.bg_larger_green)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BabyHealthFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BabyHealthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): BabyHealthFragment = BabyHealthFragment()
    }
}