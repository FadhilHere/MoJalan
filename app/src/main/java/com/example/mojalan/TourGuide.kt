package com.example.mojalan

import android.os.Parcel
import android.os.Parcelable

data class TourGuide(
    var name: String = "",
    var rating: String = "",
    var tag1: String = "",
    var tag2: String = "",
    var location: String = "",
    var description: String = "",
    var languages: String = "",
    var price: String = "",
    var imageUrl: String = "",
    var experienceUrl: String = "",
    var customerCount: Int = 0,
    var tourCount: Int = 0,
    var totalEarnings: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(rating)
        parcel.writeString(tag1)
        parcel.writeString(tag2)
        parcel.writeString(location)
        parcel.writeString(description)
        parcel.writeString(languages)
        parcel.writeString(price)
        parcel.writeString(imageUrl)
        parcel.writeString(experienceUrl)
        parcel.writeInt(customerCount)
        parcel.writeInt(tourCount)
        parcel.writeDouble(totalEarnings)
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
