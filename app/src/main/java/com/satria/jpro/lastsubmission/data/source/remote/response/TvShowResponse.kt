package com.satria.jpro.lastsubmission.data.source.remote.response
//copyright satria junanda//
import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var name: String? = null,
    @SerializedName("overview")
    var desc: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("backdrop_path")
    var imgPreview: String? = null
)