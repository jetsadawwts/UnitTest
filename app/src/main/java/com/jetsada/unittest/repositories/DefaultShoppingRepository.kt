package com.jetsada.unittest.repositories

import androidx.lifecycle.LiveData
import com.jetsada.unittest.data.local.ShoppingDao
import com.jetsada.unittest.data.local.ShoppingItem
import com.jetsada.unittest.data.local.ShoppingItemDatabase
import com.jetsada.unittest.data.remote.PixabayAPI
import com.jetsada.unittest.data.remote.responses.ImageResponse
import com.jetsada.unittest.unit.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import java.security.cert.Extension
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
): ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        return shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        return shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return  shoppingDao.observeTotalShoppingItems()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

}