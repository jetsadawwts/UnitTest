package com.jetsada.unittest.unit

import android.content.Context

class ResourceComparer {

    fun isEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
    }

}