package ru.otus.deletethis.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.otus.deletethis.R
import ru.otus.deletethis.databinding.ActivityMainBinding
import ru.otus.deletethis.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ShopListViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        viewModel.shopList.observe(this) {
            shopListAdapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val recyclerview = binding.rvShopList
        shopListAdapter = ShopListAdapter()
        recyclerview.adapter = shopListAdapter
        setupLongClickListener()
        setupClickListener()
        setupItemTouchHelper(recyclerview)

    }

    private fun setupItemTouchHelper(recyclerview: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.shopList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClickListener = {
            viewModel.getDescShopItem(it)
        }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListen = {
            viewModel.editShopItem(it)
        }
    }
}