package com.jetsada.unittest.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.jetsada.unittest.getOrAwaitValueTest
import com.jetsada.unittest.repositories.FakeShoppingRepositoryUnitTest
import com.jetsada.unittest.unit.Constants
import com.jetsada.unittest.unit.Status
import com.jetsada.unittest.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest {
    private lateinit var viewModel: ShoppingViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = ShoppingViewModel(FakeShoppingRepositoryUnitTest())
    }

    @Test
    fun `insert shopping item with empty field, return false`() {
        viewModel.insertShoppingItem("name", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name , return error`() {
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem(string, "5", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long price , return error`() {
        val string = buildString {
            for(i in 1..Constants.MAX_PRICE_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem("string", "5", string)
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with high amount, return error`() {

        viewModel.insertShoppingItem("string", "9999999999999999999", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input, return success`() {

        viewModel.insertShoppingItem("string", "5", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}