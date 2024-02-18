package com.example.account.ui.customview

import android.content.Context
import android.icu.text.CaseMap.Title
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.account.R
import com.example.account.databinding.AccountFieldViewBinding

class AccountFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: AccountFieldViewBinding
    @DrawableRes private val iconResId: Int
    private val title: String
    init {
        binding = AccountFieldViewBinding.bind(inflate(context, R.layout.account_field_view, this))

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AccountFieldView)
        iconResId = typedArray.getResourceId(R.styleable.AccountFieldView_accountFieldIcon, 0)
        title = typedArray.getString(R.styleable.AccountFieldView_accountFieldTitle) ?: ""
        typedArray.recycle()

        setData()
    }

    private fun setData() = with(binding) {
        icon.setImageDrawable(ContextCompat.getDrawable(context, iconResId))
        title.text = this@AccountFieldView.title
    }


    fun setContent(title: String? = null, extraText: String? = null) {

        title?.let { titleText ->
            binding.title.text = titleText
        }

        extraText?.let { eText ->
            binding.extraText.apply {
                this.isVisible = true
                text = eText
            }
        }

    }
}