package com.brickmate.cube.ui.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.sharedPrefs
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.utils.TAG
import kotlinx.android.synthetic.main.fragment_baby_name.*
import kotlinx.android.synthetic.main.fragment_baby_weight.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BabyWeightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BabyWeightFragment : BaseFragment() {
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

    override fun layoutId() = R.layout.fragment_baby_weight

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showArrow()
        initListener()
    }

    private fun initListener() {
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        getData()
        val fragDes = BabyAvatarFragment.newInstance()
        navigateToFragment(fragDes, fragDes.TAG())
    }

    private fun getData() {
        val value = edBabyWeight.text.toString().toDoubleOrNull()
        if (value != null) {
            sharedPrefs.setWeight(value)
        } else {
            sharedPrefs.setWeight(0.0)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BabyWeightFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BabyWeightFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): BabyWeightFragment = BabyWeightFragment()
    }
}