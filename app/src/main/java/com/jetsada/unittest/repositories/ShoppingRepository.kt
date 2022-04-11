package com.jetsada.unittest.repositories

import androidx.lifecycle.LiveData
import com.jetsada.unittest.data.local.ShoppingItem
import com.jetsada.unittest.data.remote.responses.ImageResponse
import com.jetsada.unittest.unit.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

}