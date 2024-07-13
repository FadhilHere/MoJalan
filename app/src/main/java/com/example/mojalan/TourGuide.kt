package com.example.mojalan

import android.os.Parcel
import android.os.Parcelable

data class TourGuide(
    val imageResId: Int,
    val name: String,
    val rating: String,
    val tag1: String,
    val tag2: String,
    val location: String,
    val description: String,
    val languages: String,
    val price: String,
    val experience: List<Int>?, // Optional
    val comments: List<Comment>? // Optional
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createIntArray()?.toList(),
        parcel.createTypedArrayList(Comment.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResId)
        parcel.writeString(name)
        parcel.writeString(rating)
        parcel.writeString(tag1)
        parcel.writeString(tag2)
        parcel.writeString(location)
        parcel.writeString(description)
        parcel.writeString(languages)
        parcel.writeString(price)
        parcel.writeIntArray(experience?.toIntArray())
        parcel.writeTypedList(comments)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TourGuide> {
        override fun createFromParcel(parcel: Parcel): TourGuide {
            return TourGuide(parcel)
        }

        override fun newArray(size: Int): Array<TourGuide?> {
            return arrayOfNulls(size)
        }
    }
}
