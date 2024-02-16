package com.example.core.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.core.interfaces.ProductAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUi(
    override val id: String,
    val oldPrice: Int,
    val newPrice: Int,
    val unit: String,
    val discount: Int,
    val title: String,
    val subtitle: String,
    @DrawableRes val drawableResIds: List<Int> = getDrawableResIds(id),
    val rating: Double?,
    val feedbackCounts: Int?,
    val tags: List<String>,
    var isFavorite: Boolean = false,
    val description: String,
    val ingredients: String,
    val info: List<Pair<String, String>>
) : ProductAdapterItem, Parcelable

private fun getDrawableResIds(id: String): List<Int> =
    when (id) {
        "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(com.example.core.R.drawable.image6, com.example.core.R.drawable.image5)
        "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(com.example.core.R.drawable.image1, com.example.core.R.drawable.image2)
        "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(com.example.core.R.drawable.image5, com.example.core.R.drawable.image6)
        "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(com.example.core.R.drawable.image3, com.example.core.R.drawable.image4)
        "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(com.example.core.R.drawable.image2, com.example.core.R.drawable.image3)
        "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(com.example.core.R.drawable.image6, com.example.core.R.drawable.image1)
        "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(com.example.core.R.drawable.image4, com.example.core.R.drawable.image3)
        "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(com.example.core.R.drawable.image1, com.example.core.R.drawable.image3)
        else -> listOf(com.example.core.R.drawable.image1, com.example.core.R.drawable.image2)
    }