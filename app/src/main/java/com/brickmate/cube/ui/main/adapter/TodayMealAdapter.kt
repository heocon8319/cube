package com.brickmate.cube.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.brickmate.cube.R
import com.brickmate.cube.model.TodayMeal
import com.brickmate.cube.ui.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_item_today_meal.view.*

class TodayMealAdapter(private val meals: ArrayList<TodayMeal>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var onItemClick: ((TodayMeal) -> Unit)? = null
    var onImageMLClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return TodayMealHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_today_meal,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = meals[position]
        (holder as BaseViewHolder<Any>).bind(item)
    }

    override fun getItemCount(): Int {
        return meals.size;
    }

    fun addItem(item: TodayMeal) {
        meals.add(item)
        notifyDataSetChanged()
    }

    fun addItems(items: ArrayList<TodayMeal>) {
        meals.addAll(items)
        notifyDataSetChanged()
    }

    inner class TodayMealHolder(itemView: View) : BaseViewHolder<TodayMeal>(itemView) {
        private var tvTodayML = itemView.tvTodayML
        private var ivTodayML = itemView.ivTodayML
        private var clToday = itemView.clToday
        private var clTodayML = itemView.clTodayML
        private var tvTodayIngredient = itemView.tvTodayIngredient
        private var llTodayLock = itemView.llTodayLock
        private var tvTodayClock = itemView.tvTodayClock

        override fun bind(item: TodayMeal) {
            clToday.setBackgroundResource(item.getBackground())

            tvTodayML.text = String.format(
                itemView.resources.getString(R.string.screen_today_text_ml),
                item.capacity
            )

            ivTodayML.setImageResource(item.getIconMeal())
            clTodayML.setOnClickListener {
                onImageMLClick?.invoke(item.capacity.toString())

            }

            tvTodayIngredient?.text = item.getIngredientString()

            llTodayLock.visibility = item.getVisibleClock()
            tvTodayClock.text = item.time

            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}