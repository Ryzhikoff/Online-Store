package com.example.feature_favorites.models

import com.example.database.domain.ProductIsFavoritesUseCase
import com.example.core.models.ProductUi
import com.example.remote.data.dto.Info
import com.example.remote.data.dto.ProductListDto

suspend fun ProductListDto.toProductUiList(isFavoritesUseCase: ProductIsFavoritesUseCase): List<ProductUi> {
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
            tags = item.tags,
            isFavorite = isFavoritesUseCase.execute(item.id),
            description = item.description,
            ingredients = item.ingredients,
            info = getListPairs(item.info),
            available = item.available,
        )
    }
    return list
}

fun getListPairs(info: List<Info>): List<Pair<String, String>> {
    val list = mutableListOf<Pair<String, String>>()
    info.forEach {
        list.add(Pair(it.title, it.value))
    }
    return list
}




