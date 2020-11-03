package com.brickmate.cube.ui.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.brickmate.cube.R
import com.brickmate.cube.model.Ingredient

class IngredientsAdapter(private val ingredients: ArrayList<Ingredient>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return ingredients.size
    }

    override fun getItem(position: Int): Any {
        return ingredients[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = convertView
        var holder: ItemHolder

        if (myView == null) {
            myView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_item_ingredient, parent, false)
            holder = ItemHolder()
            holder.name = myView.findViewById<TextView>(R.id.tvNameIngredient)
            holder.background = myView.findViewById<ImageView>(R.id.ivBGIngredient)
            myView.tag = holder
        } else {
            holder = myView.tag as ItemHolder
        }
        val item = ingredients[position]
        holder.name?.text = item.name
        holder.background?.setBackgroundResource(item.getLayout())

        return myView!!
    }

    internal class ItemHolder {
        var name: TextView? = null
        var background: ImageView? = null
    }
}