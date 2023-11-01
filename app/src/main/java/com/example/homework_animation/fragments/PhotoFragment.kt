package com.example.homework_animation.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.homework_animation.R
import com.example.homework_animation.databinding.FragmentGalleryBinding
import com.example.homework_animation.databinding.FragmentPhotoBinding

class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = PhotoFragmentArgs.fromBundle(requireArguments())
        binding.imageView.transitionName = args.imageTransitionName
        binding.textViewLabel.transitionName = args.labelTransitionName
        binding.imageView.setImageResource(args.imageResId)
        binding.textViewLabel.text = args.labelText

        setupImageRotationAnimation(view)

        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    private fun setupImageRotationAnimation(view: View) {
        val imageView = binding.imageView
        imageView.setOnClickListener {
            val animator = ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 360.0f)
            animator.duration = 1000
            animator.start()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
