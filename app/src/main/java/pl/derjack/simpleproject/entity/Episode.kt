package pl.derjack.simpleproject.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import pl.derjack.simpleproject.utils.DateTimeUtils

@SuppressLint("ParcelCreator")
@Parcelize
data class Episode(val id: Long = 0,
                   val url: String?,
                   val name: String?,
                   val season: Int = 0,
                   val number: Int = 0,
                   val airdate: String?,
                   val airtime: String?,
                   val airstamp: String?,
                   val runtime: Int = 0,
                   val mediumImage: String?,
                   val originalImage: String?,
                   val summary: String?) : Parcelable, Comparable<Episode> {

    @delegate:Transient val datetime: DateTime by lazy { DateTimeUtils.getEpisodeDateTime(airstamp) }

    override fun compareTo(other: Episode): Int {
        return datetime.compareTo(other.datetime)
    }

}