package ru.otus.deletethis.domain

class GetShopItemInterActor(private val shopListRepository: ShopListRepository) {
    fun getShopItem(itemId: Int): ShopItem {
        return shopListRepository.getShopItem(itemId)
    }
}