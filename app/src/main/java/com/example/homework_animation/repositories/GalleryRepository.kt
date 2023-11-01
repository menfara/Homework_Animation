package com.example.homework_animation.repositories

import com.example.homework_animation.R
import com.example.homework_animation.models.GalleryItem

class GalleryRepository {

    fun getGalleryItems(): List<GalleryItem> {
        return listOf(
            GalleryItem(R.drawable.img_1, "Улица"),
            GalleryItem(R.drawable.img_2, "Реклама"),
            GalleryItem(R.drawable.img_3, "Статуя Свободы"),
            GalleryItem(R.drawable.img_4, "Статуя Свободы"),
            GalleryItem(R.drawable.img_5, "Пёсик"),
            GalleryItem(R.drawable.img_6, "Мост"),

        )
    }
}
