package com.brickmate.cube.ui.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.replaceFragment
import kotlinx.android.synthetic.main.fragment_main_login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainLoginFragment : BaseFragment() {
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

    override fun layoutId() = R.layout.fragment_main_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        val emailLoginFrag = EmailLoginFragment.newInstance()
        llEmail.setOnClickListener {
            replaceFragment(
                R.id.clContainer,
                emailLoginFrag,
                true,
                emailLoginFrag.TAG()
            )
        }

        val nickNameFrag = NickNameFragment.newInstance()
        llNaver.setOnClickListener {
            replaceFragment(
                R.id.clContainer,
                nickNameFrag,
                true,
                nickNameFrag.TAG()
            )
        }
        llKakao.setOnClickListener {
            replaceFragment(
                R.id.clContainer,
                nickNameFrag,
                true,
                nickNameFrag.TAG()
            )
        }

        val emailRegisterFrag = EmailRegisterFragment.newInstance()
        llRegisterByEmail.setOnClickListener {
            replaceFragment(
                R.id.clContainer,
                emailRegisterFrag,
                true,
                emailRegisterFrag.TAG()
            )
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainLoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainLoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): MainLoginFragment = MainLoginFragment()
    }
}