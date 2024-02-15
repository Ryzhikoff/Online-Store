package com.example.catalog.models

import androidx.annotation.DrawableRes
import com.example.catalog.R
import com.example.catalog.ui.rv.ProductAdapterItem
import com.example.remote.data.dto.ProductListDto

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
) : ProductAdapterItem

fun ProductListDto.toProductUiList(): List<ProductUi> {
    val list = mutableListOf<ProductUi>()
    items.forEach { item ->
        list += ProductUi(
            id = item.id,
            oldPrice = item.price.price.toInt(),
            newPrice = item.price.priceWithDiscount.toInt(),
            unit = item.price.unit,
            discount = item.price.discount,
            title = item.title,
            subtitle = item.subtitle,
            rating = item.feedback?.rating,
            feedbackCounts = item.feedback?.count,
            tags = item.tags
        )
    }
    return list
}

private fun getDrawableResIds(id: String): List<Int> =
    when (id) {
        "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(R.drawable.image6, R.drawable.image5)
        "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(R.drawable.image1, R.drawable.image2)
        "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(R.drawable.image5, R.drawable.image6)
        "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.image3, R.drawable.image4)
        "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.image2, R.drawable.image3)
        "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.image6, R.drawable.image1)
        "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.image4, R.drawable.image3)
        "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.image1, R.drawable.image3)
        else -> listOf(R.drawable.image1, R.drawable.image2)
    }
