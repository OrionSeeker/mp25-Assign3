package com.michael.assignment3.model
import android.os.Parcel
import android.os.Parcelable

data class User(val name:String, val email:String, val password:String) : Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR: Parcelable.Creator<User>{
        override fun createFromParcel(parcel: Parcel): User = User(parcel)
        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
    }
}