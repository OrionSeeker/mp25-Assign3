package com.michael.assignment3.model

import android.os.Parcel
import android.os.Parcelable

data class TandaTangan(val penerima: ArrayList<String>, val perihal:ArrayList<String>): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.createStringArrayList() ?: ArrayList(),
        parcel.createStringArrayList() ?: ArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(penerima)
        parcel.writeStringList(perihal)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR: Parcelable.Creator<TandaTangan>{
        override fun createFromParcel(parcel: Parcel): TandaTangan = TandaTangan(parcel)
        override fun newArray(size: Int): Array<TandaTangan?> = arrayOfNulls(size)
    }
}