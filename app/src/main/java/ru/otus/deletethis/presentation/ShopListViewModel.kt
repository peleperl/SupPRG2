package ru.otus.deletethis.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.otus.deletethis.data.ShopListRepositoryImpl
import ru.otus.deletethis.domain.*

class ShopListViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl()

    private val getShopListInterActor = GetShopListInterActor(repository)
    private val deleteShopItemInterActor = DeleteShopItemInterActor(repository)
    private val editShopItemActor = EditShopItemInterActor(repository)

    val shopList = getShopListInterActor.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemInterActor.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem) {
        editShopItemActor.editShopItem(shopItem)
    }

    fun getDescShopItem(shopItem: ShopItem){
        Log.i("MER", shopItem.name + " " + shopItem.count)
    }

}