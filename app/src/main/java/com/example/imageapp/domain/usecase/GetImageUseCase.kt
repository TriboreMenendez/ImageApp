package com.example.imageapp.domain.usecase

import com.example.imageapp.domain.model.ImageDomainModel
import com.example.imageapp.domain.repository.ImageRepository

class GetImageUseCase(private val imageRepository: ImageRepository) {

    suspend fun getImage(): List<ImageDomainModel> {
        return imageRepository.getImage()
    }
}