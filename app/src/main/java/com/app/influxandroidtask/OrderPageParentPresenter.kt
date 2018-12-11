package com.app.influxandroidtask

import android.content.Context

class OrderPageParentPresenter(val context: Context) {

    private val model = OrderPageParentModel()

    fun getModel(): OrderPageParentModel {
        return model
    }

    fun addProductToCart(item: ItemModel) {
        model.finalProductListMap[item.itemCode] = item
        if (item.itemQuantity == 0) {
            if (model.finalProductListMap.contains(item.itemCode)) {
                model.finalProductListMap.remove(item.itemCode)
            }
        }
    }

}
