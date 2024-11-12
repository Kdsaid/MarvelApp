package com.example.data.network.models

data class ImageDto(
    val path: String?,
    val extension: String?,
)

fun ImageDto.toThumbnailUrl(): String? {
    return path?.takeIf { "image_not_available" !in it }?.let {
        "$path.$extension"
    }
}