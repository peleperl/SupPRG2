package ru.otus.deletethis.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.otus.deletethis.domain.ShopItem
import ru.otus.deletethis.domain.ShopListRepository

class ShopListRepositoryImpl : ShopListRepository {
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrementID = 0

    private val shopListLD = MutableLiveData<List<ShopItem>>()

    init {
        var item = ShopItem("Name", 3, true)
        for (i in 0 until 1000){
            item = if (i%2==0){
                ShopItem("NAme $i", i, true)
            }else{
                ShopItem("NAme $i", i, false)
            }

            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINDED_ID) {
            shopItem.id = autoIncrementID++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enable = !shopItem.enable)
        deleteShopItem(shopItem)
        addShopItem(newItem)
    }

    override fun getShopItem(itemId: Int): ShopItem {
        return shopList.find { it.id == itemId } ?: throw RuntimeException("E")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    fun updateList() {
        shopListLD.value = shopList.toList()
    }
}