package com.brickmate.cube.ui.main.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.brickmate.cube.R
import com.brickmate.cube.model.Material
import com.brickmate.cube.model.TodayMeal
import com.brickmate.cube.ui.main.adapter.TodayMaterialAdapter
import com.brickmate.cube.utils.toast
import kotlinx.android.synthetic.main.dialog_today_edit_meal.*


class TodayEditMealDialog : DialogFragment() {

    private var meal = TodayMeal()
    private var materialSelected: Material? = null
    private var materialIndex: Int? = null
    lateinit var materialAdapter: TodayMaterialAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_today_edit_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initListener()
        initRecycleView()
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setMeal(mealObj: TodayMeal) {
        meal = mealObj
    }

    private fun initData() {
        tvTitle.text = meal.name
        tvTodayIngredient.text = meal.getIngredientString()
    }

    private fun initListener() {
        var data = ArrayList<String>()
        data.add("1")
        data.add("2")
        data.add("3")

        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        snTodayAsh.adapter = adapter

        snTodayAsh.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                if (materialSelected == null || materialIndex == null) {
                    toast("Please choose item")
                } else {
                    updateData(parentView?.getItemAtPosition(position).toString())

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initRecycleView() {
        var dummy: ArrayList<Material> = ArrayList()
        dummy.add(
            Material(
                "감자",
                R.drawable.ic_potato,
                "큐브",
                15,
                15,
                1
            )
        )
        dummy.add(
            Material(
                "소고기",
                R.drawable.ic_meat,
                "큐브",
                15,
                15,
                1
            )
        )
        dummy.add(
            Material(
                "쌀",
                R.drawable.ic_rice,
                "스굽",
                15,
                45,
                3
            )
        )

        rvTodayIngredient.layoutManager = LinearLayoutManager(activity)
        rvTodayIngredient.setHasFixedSize(true)

        val divider = DividerItemDecoration(rvTodayIngredient.context, DividerItemDecoration.VERTICAL)
        val img = context?.let { ContextCompat.getDrawable(it, R.drawable.custom_divider) }
        img?.let { divider.setDrawable(it) }
        rvTodayIngredient.addItemDecoration(divider)

        var adapter = activity?.let { TodayMaterialAdapter(dummy, it) }
        if (adapter != null) {
            materialAdapter = adapter
        }
        adapter?.onItemClick = { item, index ->
            materialSelected = item
            materialIndex = index

        }
        rvTodayIngredient.adapter = adapter
    }

    private fun updateData(item: String) {
        val value = item.toInt()
        materialSelected?.count = value
        materialSelected?.weight = materialSelected?.rootWeight?.times(value)!!
        materialAdapter.updateItem(materialSelected!!, materialIndex!!)
    }

    companion object {
        fun newInstance(): TodayEditMealDialog = TodayEditMealDialog()
    }
}