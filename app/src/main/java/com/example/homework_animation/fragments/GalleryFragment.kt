package com.example.homework_animation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_animation.R
import com.example.homework_animation.adapters.GalleryAdapter
import com.example.homework_animation.adapters.GridSpacingItemDecoration
import com.example.homework_animation.databinding.FragmentGalleryBinding
import com.example.homework_animation.databinding.FragmentSplashBinding
import com.example.homework_animation.viewmodels.GalleryViewModel

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GalleryViewModel
    private lateinit var adapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]


        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = gridLayoutManager
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.grid_spacing)
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels, true))



        viewModel.galleryItems.observe(viewLifecycleOwner) { items ->
            adapter = GalleryAdapter(items)
            binding.recyclerView.adapter = adapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}