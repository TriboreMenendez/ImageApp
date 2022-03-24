package com.example.imageapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentFullscreenBinding
import com.example.imageapp.utils.Const

class FullscreenFragment : Fragment() {
    private lateinit var binding: FragmentFullscreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullscreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = arguments?.getString(Const.IMAGE_URL)

        Glide
            .with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_load_image)
            .error(R.drawable.ic_error_image)
            .fallback(R.drawable.ic_load_image)
            .centerCrop()
            .into(binding.itemImageView)
    }
}