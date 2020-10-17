package com.example.madlevel7task2.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    val id: Long?,
    val question: String?,
    val choices: List<String>,
    val correctAnswer: String?
): Parcelable