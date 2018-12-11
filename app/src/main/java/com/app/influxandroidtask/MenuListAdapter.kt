package com.app.influxandroidtask

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class MenuListAdapter(private val menuList: MutableList<ItemModel>, private val onCallBackListener: MenuListView, private val context: Context) : RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_menu_list_row, parent, false))
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemModel = menuList[position]
        holder.bind(itemModel, onCallBackListener, context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var itemName: TextView = itemView.findViewById(R.id.tv_item_name)
        private var itemCode: TextView = itemView.findViewById(R.id.tv_item_code)
        private var itemQuantity: TextView = itemView.findViewById(R.id.tv_qty)
        private var itemMinus: TextView = itemView.findViewById(R.id.tv_minus)
        private var itemPlus: TextView = itemView.findViewById(R.id.tv_plus)
        private var imgItemImage: ImageView = itemView.findViewById(R.id.img_item_image)

        fun bind(item: ItemModel, onCallBackListener: MenuListView, context: Context) {
            itemName.text = item.itemName
            itemCode.text = item.itemCode
            itemQuantity.text = item.itemQuantity.toString()
            Picasso.with(context)
                    .load(item.itemImage!!)
                    .placeholder(R.drawable.loadingimage)
                    .error(R.drawable.nopicture)
                    .into(imgItemImage)

            val curveRadius = 20F
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgItemImage.outlineProvider = object : ViewOutlineProvider() {
                    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                    override fun getOutline(view: View?, outline: Outline?) {
                        outline?.setRoundRect(0, 0, view!!.width, (view.height + curveRadius).toInt(), curveRadius)
                    }
                }
                imgItemImage.clipToOutline = true
            }

            itemMinus.setOnClickListener {
                item.itemQuantity = item.itemQuantity - 1
                if (item.itemQuantity < 0) {
                    item.itemQuantity = 0
                }
                itemQuantity.text = item.itemQuantity.toString()
                onCallBackListener.onItemCountAdjust(item)
            }
            itemPlus.setOnClickListener {
                item.itemQuantity = item.itemQuantity + 1
                if (item.itemQuantity > 100) {
                    item.itemQuantity = 99
                }
                itemQuantity.text = item.itemQuantity.toString()
                onCallBackListener.onItemCountAdjust(item)
            }
        }


    }

}
