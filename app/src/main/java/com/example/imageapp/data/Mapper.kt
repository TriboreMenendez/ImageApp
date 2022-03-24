package com.example.imageapp.data

import com.example.imageapp.domain.model.ImageDomainModel

fun String.toDomain(): ImageDomainModel {
    return ImageDomainModel(imageUrl = this)
}