package com.example.details.ui.cistomview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.example.details.R
import com.example.details.databinding.StarsViewBinding
import kotlin.math.ceil
import kotlin.math.floor

class StarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: StarsViewBinding
    private val stars: List<ImageView>

    init {
        binding = StarsViewBinding.bind(inflate(context, R.layout.stars_view, this))
        stars = with(binding) {
            listOf(
                star1, star2, star3, star4, star5
            )

        }
    }

    fun setRating(rating: Double) {
        val rate = if (rating < MIN_RATING) {
            MIN_RATING
        } else if (rating > MAX_RATING) {
            MAX_RATING
        } else {
            rating
        }
        if (rate >= MAX_RATING - TOP_RANGE) {
            setFullStars(MAX_RATING.toInt())
        } else {
            var fullStars: Int = floor(rate).toInt()
            val remainder: Double = rate - fullStars
            var emptyStars: Int = MAX_RATING.toInt() - ceil(rate).toInt()
            when (remainder) {
                in MIN_RATING.. BOTTOM_RANGE -> {
                    emptyStars++
                }

                in TOP_RANGE..MAX_RATING -> {
                    fullStars++
                }

                else -> {
                    setHalfStar(fullStars)
                }
            }

            setFullStars(fullStars)
            setEmptyStars(emptyStars)
        }
    }

    private fun setFullStars(fullStars: Int) {
        stars.forEachIndexed { index, imageView ->
            if (index < fullStars) {
                imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, com.example.core.R.drawable.icon_star_full, context.theme))
            }
        }
    }

    private fun setEmptyStars(emptyStar: Int) {
        stars.forEachIndexed { index, imageView ->
            if (index >= MAX_RATING.toInt() - emptyStar) {
                imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, com.example.core.R.drawable.icon_star_empty, context.theme))
            }
        }
    }

    private fun setHalfStar(index: Int) {
        stars[index].setImageDrawable(ResourcesCompat.getDrawable(resources, com.example.core.R.drawable.icon_star_half, context.theme))
    }

    companion object {
        const val MAX_RATING = 5.0
        const val MIN_RATING = 0.0
        const val BOTTOM_RANGE = 0.3
        const val TOP_RANGE = 0.7
    }
}