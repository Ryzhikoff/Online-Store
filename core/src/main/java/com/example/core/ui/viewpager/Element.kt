package com.example.core.ui.viewpager

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.core.R
import com.example.core.ui.viewpager.VpAdapter.Companion.KEY_DRAWABLE_RES_ID

class Element : Fragment(R.layout.fragment_element) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.image)

        val drawResId = arguments?.getInt(KEY_DRAWABLE_RES_ID)

        drawResId?.let { resId ->
            Glide.with(this)
                .load(ResourcesCompat.getDrawable(resources, resId, requireContext().theme))
                .transform(CenterInside(), RoundedCorners(resources.getDimension(R.dimen.corner_radius).toInt()))
                .into(imageView)
        }
    }
}