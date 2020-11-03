package com.brickmate.cube.ui.login.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.toast
import kotlinx.android.synthetic.main.fragment_main_term.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainTermFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainTermFragment : BaseFragment() {
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

    override fun layoutId() = R.layout.fragment_main_term

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showArrow()
        tvTermUseExpand.movementMethod = ScrollingMovementMethod()
        tvCollectUseExpand.movementMethod = ScrollingMovementMethod()
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        if (cbTermUse.isChecked && cbCollectUse.isChecked) {
            val fragDes = GenderFragment.newInstance()
            navigateToFragment(fragDes, fragDes.TAG())
        } else {
            toast(getString(R.string.required_text))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainTermFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainTermFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): MainTermFragment = MainTermFragment()

    }
}