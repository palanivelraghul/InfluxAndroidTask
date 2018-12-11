package com.app.influxandroidtask

import com.app.influxandroidtask.application.InfluxApplication


class MenuListPresenter(val menuListFragmentListener: MenuListView) {

    private val model = MenuListModel()

    fun getModel(): MenuListModel {
        return model
    }

    fun initializeMenuList(menuCategory: String?) {
        var menuList = mutableListOf<ItemModel>()
        val resources = InfluxApplication.instance.resources
        when (menuCategory) {
            resources.getString(R.string.combos) -> {
                menuList.add(getItemData("BURGER PIZZA- CLASSIC VEG", "BP-10", 150, R.drawable.dominos_20pizza_20burger))
                menuList.add(getItemData("BURGER PIZZA- PREMIUM VEG", "BP-11", 180, R.drawable.default_item_image))
                menuList.add(getItemData("BURGER PIZZA- CLASSIC NON VEG", "BP-12", 250, R.drawable.dominos_20pizza_20burger))
                menuList.add(getItemData("VEG LOADED PIZZA", "VP-10", 100, R.drawable.dominos_20pizza_20burger))
                menuList.add(getItemData("CHEESY PIZZA", "CP-10", 70, R.drawable.dominos_20pizza_20burger))
                menuList.add(getItemData("CAPSICUM PIZZA", "MP-10", 210, R.drawable.dominos_20pizza_20burger))
                menuList.add(getItemData("ONION PIZZA", "OP-10", 70, R.drawable.dominos_20pizza_20burger))
                menuList.add(getItemData("GOLDEN CORN PIZZA", "GCP-10", 110, R.drawable.default_item_image))
                menuList.add(getItemData("PANEER & ONION PIZZA", "POP-10", 140, R.drawable.dominos_20pizza_20burger))
                menuList.add(getItemData("PEPPER BARBECUE CHICKEN PIZZA", "PBCP-10", 270, R.drawable.default_item_image))
                menuList.add(getItemData("CHICKEN SAUSAGE PIZZA", "CSP-10", 320, R.drawable.dominos_20pizza_20burger))
                model.combosMenuList = menuList
            }
            resources.getString(R.string.drinks) -> {
                menuList.add(getItemData("COCO", "CO-10", 100, R.drawable.coco))
                menuList.add(getItemData("PEPSI", "PS-11", 90, R.drawable.pepsi))
                menuList.add(getItemData("7UP", "UP-12", 70, R.drawable.default_drinks_image))
                menuList.add(getItemData("MAZZA", "MZ-10", 80, R.drawable.default_drinks_image))
                menuList.add(getItemData("SPRITE", "SP-10", 70, R.drawable.sprite))
                menuList.add(getItemData("BOVONTO", "BT-10", 30, R.drawable.bonvonto))
                menuList.add(getItemData("FANTA", "FT-10", 70, R.drawable.fanta))
                menuList.add(getItemData("ICE DRINKS", "ID-10", 10, R.drawable.default_drinks_image))
                menuList.add(getItemData("COCONUT DRINKS", "CD-10", 40, R.drawable.default_drinks_image))
                model.combosMenuList = menuList
            }
            else -> {
                menuList.add(getItemData("BURGER PIZZA- CLASSIC VEG", "BP-10", 150, R.drawable.default_item_image))
                menuList.add(getItemData("BURGER PIZZA- PREMIUM VEG", "BP-11", 180, R.drawable.default_item_image))
                menuList.add(getItemData("BURGER PIZZA- CLASSIC NON VEG", "BP-12", 250, R.drawable.default_item_image))
                menuList.add(getItemData("VEG LOADED PIZZA", "VP-10", 100, R.drawable.default_item_image))
                menuList.add(getItemData("CHEESY PIZZA", "CP-10", 70, R.drawable.default_item_image))
                menuList.add(getItemData("CAPSICUM PIZZA", "MP-10", 210, R.drawable.default_item_image))
                menuList.add(getItemData("ONION PIZZA", "OP-10", 70, R.drawable.default_item_image))
                menuList.add(getItemData("GOLDEN CORN PIZZA", "GCP-10", 110, R.drawable.default_item_image))
                menuList.add(getItemData("PANEER & ONION PIZZA", "POP-10", 140, R.drawable.default_item_image))
                menuList.add(getItemData("PEPPER BARBECUE CHICKEN PIZZA", "PBCP-10", 270, R.drawable.default_item_image))
                menuList.add(getItemData("CHICKEN SAUSAGE PIZZA", "CSP-10", 320, R.drawable.default_item_image))
                model.combosMenuList = menuList
            }
        }

    }

    private fun getItemData(name: String, code: String, prize: Int, drawable: Int): ItemModel {
        var itemModel = ItemModel()
        itemModel.itemName = name
        itemModel.itemCode = code
        itemModel.itemImage = drawable
        itemModel.itemPrice = prize
        itemModel.itemQuantity = 0
        return itemModel
    }


}
