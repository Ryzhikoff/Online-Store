package com.example.details.ui.cistomview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.details.R
import com.example.details.databinding.CharacteristicsViewBinding

class CharacteristicsView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CharacteristicsViewBinding
    init {
        binding = CharacteristicsViewBinding.bind(inflate(context, R.layout.characteristics_view, this))
    }

    constructor(
        context: Context,
        title: String,
        value: String
    ): this(context) {
        setData(title, value)
    }

    private fun setData(title: String, value: String) {
        binding.title.text = title
        binding.value.text = value
    }
}