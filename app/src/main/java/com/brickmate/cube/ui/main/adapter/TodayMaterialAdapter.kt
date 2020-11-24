package com.brickmate.cube.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brickmate.cube.R
import com.brickmate.cube.model.Material
import com.brickmate.cube.ui.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_item_today_edit_meal.view.*


class TodayMaterialAdapter(private val materials: ArrayList<Material>, private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var onItemClick: ((Material, Int) -> Unit)? = null
    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return TodayMealHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_today_edit_meal,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = materials[position]
        if (selectedPosition == position)
            holder.itemView.clBackground.setBackgroundColor(Color.parseColor("#FFFAFA"));
        else
            holder.itemView.clBackground.setBackgroundColor(Color.parseColor("#FFFFFF"));
        (holder as BaseViewHolder<Any>).bind(item)
    }

    override fun getItemCount(): Int {
        return materials.size;
    }

    fun addItem(item: Material) {
        materials.add(item)
        notifyDataSetChanged()
    }

    fun updateItem(item: Material, index: Int) {
        materials[index] = item
        notifyItemChanged(index)
    }

    fun addItems(items: ArrayList<Material>) {
        materials.addAll(items)
        notifyDataSetChanged()
    }

    inner class TodayMealHolder(itemView: View) : BaseViewHolder<Material>(itemView) {
        var clBackground = itemView.clBackground
        private var ivImage = itemView.ivImage
        private var tvName = itemView.tvName
        private var tvDescription = itemView.tvDescription

        @SuppressLint("ResourceAsColor")
        override fun bind(item: Material) {
//            clBackground.setBackgroundColor(Color.parseColor("#FFFFFF"))
            Glide.with(context).load(item.imgUrl)
                .placeholder(R.drawable.ic_baby_avatar_holder)
                .error(R.drawable.ic_baby_avatar_holder)
                .into(ivImage)

            tvName.text = item.name

            tvDescription.text = "${item.count} ${item.unit} (${item.weight}g)"

            itemView.setOnClickListener {
                selectedPosition = adapterPosition
//                clBackground.setBackgroundColor(Color.parseColor("#C4C4C4"))
                onItemClick?.invoke(item, adapterPosition)
                notifyDataSetChanged()
            }
        }
    }
}