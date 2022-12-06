package ru.otus.deletethis.domain

import androidx.lifecycle.LiveData

class GetShopListInterActor(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
     }
}