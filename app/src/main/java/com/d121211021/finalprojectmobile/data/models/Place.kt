package com.d121211021.finalprojectmobile.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Place(
    val collections: Int?, //menambahkan ke collections
    val comments: Int?, //jumlah komen
    val downloads: Int?, //berapa kali didonlot
    val id: Int?,
    val imageHeight: Int?,
    val imageSize: Int?,
    val imageWidth: Int?,
    val largeImageURL: String?, //versi lbih besar
    val likes: Int?,
    val pageURL: String?, //tempat foto sejenisnya oleh pemilik
    val previewHeight: Int?,
    val previewURL: String?, //download image
    val previewWidth: Int?,
    val tags: String?, //kata terkait
    val type: String?,
    val user: String?, //nama pemilik
    val userImageURL: String?, //ava pemilik foto
    val user_id: Int?,
    val views: Int?, //jumlah dilihat
    val webformatHeight: Int?,
    val webformatURL: String?, //foto tempat
    val webformatWidth: Int?
) :Parcelable