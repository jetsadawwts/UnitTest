package com.jetsada.unittest.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.jetsada.unittest.adapters.ImageAdapter
import com.jetsada.unittest.adapters.ShoppingItemAdapter
import com.jetsada.unittest.repositories.FakeShoppingRepositoryAndroidUnitTest
import com.jetsada.unittest.viewModel.ShoppingViewModel
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val shoppingItemAdapter: ShoppingItemAdapter,
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            ShoppingFragment::class.java.name -> ShoppingFragment(shoppingItemAdapter, ShoppingViewModel(FakeShoppingRepositoryAndroidUnitTest()))
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}