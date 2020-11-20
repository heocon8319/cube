package com.brickmate.cube.ui.main.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.brickmate.cube.R
import com.brickmate.cube.model.TodayMeal
import com.brickmate.cube.utils.toast
import kotlinx.android.synthetic.main.dialog_today_edit_meal.*

class TodayEditMealDialog : DialogFragment() {

    private var meal = TodayMeal()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_today_edit_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initListener()
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
        data.add("Item 1")
        data.add("Item 2")
        data.add("Item 3")

        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        snTodayAsh.adapter = adapter



//        snTodayAsh.setOnItemClickListener { parent, view, position, id ->
//            toast(parent.getItemAtPosition(position).toString())
//        }
    }

    companion object {
        fun newInstance(): TodayEditMealDialog = TodayEditMealDialog()
    }
}