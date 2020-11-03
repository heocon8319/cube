package com.brickmate.cube.ui.login.view

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.model.Ingredient
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.ui.login.adapter.IngredientsAdapter
import com.brickmate.cube.utils.TAG
import kotlinx.android.synthetic.main.fragment_ingredients.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GoodIngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GoodIngredientsFragment : BaseFragment() {
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

    override fun layoutId() = R.layout.fragment_ingredients

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
        initData()
    }

    private fun initView() {
        showArrow()
        showBackground()
        tvTitle.text = getString(R.string.screen_good_ingredients_title)
    }

    private fun initData() {
        var ingredients = resources.getStringArray(R.array.ingredients)

        // load foods
        var data = ArrayList<Ingredient>()
        for (element in ingredients) {
            data.add(Ingredient(element, false))
        }

        var adapter = IngredientsAdapter(data)

        gvIngredients.adapter = adapter
        gvIngredients.setOnItemClickListener { parent, v, position, id ->
            var selectedItem = data[position]
            selectedItem.isSelected = !selectedItem.isSelected

            val imageView: ImageView = (v.tag as IngredientsAdapter.ItemHolder).background!!
            imageView.setBackgroundResource(selectedItem.getLayout())
        }
    }

    private fun initListener() {
    }

    override fun onNextArrowPressed() {
        super.onNextArrowPressed()
        val fragDes = AdverseIngredientsFragment.newInstance()
        navigateToFragment(fragDes, fragDes.TAG())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GoodIngredientsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GoodIngredientsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): GoodIngredientsFragment = GoodIngredientsFragment()
    }
}