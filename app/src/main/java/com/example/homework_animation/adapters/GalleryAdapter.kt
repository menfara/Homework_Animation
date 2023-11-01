package com.example.homework_animation.adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_animation.databinding.ItemGalleryBinding
import com.example.homework_animation.fragments.GalleryFragmentDirections
import com.example.homework_animation.models.GalleryItem

class GalleryAdapter(private val items: List<GalleryItem>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GalleryItem, position: Int) {
            binding.imageView.setImageResource(item.imageResId)
            binding.textViewLabel.text = item.label

            val imageTransitionName = "transition_image_$position"
            val labelTransitionName = "transition_label_$position"

            binding.imageView.transitionName = imageTransitionName
            binding.textViewLabel.transitionName = labelTransitionName

            itemView.setOnClickListener {
                val navController = Navigation.findNavController(it)

                val extras = FragmentNavigatorExtras(
                    binding.imageView to imageTransitionName,
                    binding.textViewLabel to labelTransitionName
                )

                val action = GalleryFragmentDirections.actionGalleryFragmentToPhotoFragment(
                    imageTransitionName, labelTransitionName, item.imageResId, item.label
                )
                navController.navigate(action, extras)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    override fun getItemCount() = items.size
}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}
