package com.jetsada.unittest.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.jetsada.unittest.R
import com.jetsada.unittest.unit.ResourceComparer
import org.junit.After
import org.junit.Before

class ResourceComparerTest {
    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setUp() {
        resourceComparer = ResourceComparer()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "UnitTesting")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "Hello")
        assertThat(result).isFalse()
    }
}