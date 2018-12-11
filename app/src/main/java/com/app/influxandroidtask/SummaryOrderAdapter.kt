package com.app.influxandroidtask

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SummaryOrderAdapter(val productList: MutableList<ItemModel>, val context: Context) : RecyclerView.Adapter<SummaryOrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return SummaryOrderAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_summary_row, parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvItemName: TextView = view.findViewById(R.id.tv_item_name)
        var tvItemCost: TextView = view.findViewById(R.id.tv_item_total_value)

        fun bind(itemModel: ItemModel) {
            tvItemName.text = itemModel.itemName + " (" + itemModel.itemQuantity + " * " + itemModel.itemPrice + ")"
            tvItemCost.text = (itemModel.itemQuantity * itemModel.itemPrice).toString()
        }

    }

}
