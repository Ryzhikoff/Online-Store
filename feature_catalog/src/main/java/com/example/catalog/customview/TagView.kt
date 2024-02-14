package com.example.catalog.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.catalog.R
import com.example.catalog.databinding.TagViewBinding

class TagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: TagViewBinding

    init {
        binding = TagViewBinding.bind(inflate(context, R.layout.tag_view, this))
        if (attrs != null) {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TagView)
            setName(typeArray.getString(R.styleable.TagView_tagViewName) ?: "")
            typeArray.recycle()
        }
    }

    //Конструктор для создания в коде
    constructor(
        context: Context,
        name: String,
    ) : this(context) {
        setName(name)
    }

    private fun setName(name: String) {
        binding.name.text = name
    }

    override fun setSelected(selected: Boolean) {
        binding.icon.isVisible = selected
        binding.name.isSelected = selected
        if (selected) {
            binding.name.setTextAppearance(com.example.core.R.style.Title4)
            binding.name.setTextColor(ContextCompat.getColor(context, com.example.core.R.color.white))
        } else {
            binding.name.setTextAppearance(com.example.core.R.style.ButtonText2)
            binding.name.setTextColor(ContextCompat.getColor(context, com.example.core.R.color.gray))
        }


        super.setSelected(selected)
    }
}