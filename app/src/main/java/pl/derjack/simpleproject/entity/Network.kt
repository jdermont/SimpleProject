package pl.derjack.simpleproject.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Network(val id: Long = 0,
                   val name: String?,
                   val countryName: String?,
                   val countryCode: String?,
                   val countryTimezone: String?) : Parcelable {
}