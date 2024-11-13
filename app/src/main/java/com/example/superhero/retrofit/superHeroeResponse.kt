package com.example.superhero.retrofit

import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
data class SuperHeroeResponse (

    @SerializedName("response") val success: String,
    @SerializedName("results-for")val filteredName: String,
    @SerializedName("results") val listHero: List<DataHero>,


)

data class DataHero(
    @SerializedName("response") val success: String,
    @SerializedName("id") val numID:Int,
    @SerializedName("name")val nameHero: String,
    @SerializedName("powerstats") val powerStats: PowerStats,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearance,
    @SerializedName("work") val work: Work,
    @SerializedName("connections") val connections: Connections,
    @SerializedName("image") val urlImage: UrlImage

)
data class PowerStats(
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String
)
data class Biography(
    @SerializedName("full-name") val fullName:String,
    @SerializedName("alter-egos") val alterEgo:String,
    @SerializedName("aliases") val alias:List<String>,
    @SerializedName("place-of-birth") val placeBirth:String,
    @SerializedName("first-appearance") val firstAppearance:String,
    @SerializedName("publisher") val publisher:String,
    @SerializedName("alignment") val alignment:String

)
data class Appearance (
    @SerializedName("gender") val gender:String,
    @SerializedName("race") val race:String,
    @SerializedName("height") val height:List<String>,
    @SerializedName("weight") val weight:List<String>,
    @SerializedName("eyes-color") val eyesColor:String,
    @SerializedName("hair-color") val hairColor:String
)
data class Work(
    @SerializedName("ocuppation") val ocuppation:String,
    @SerializedName("base") val base:String
)
data class Connections(
    @SerializedName("group") val group:String,
    @SerializedName("affiliation") val affiliation:String,
    @SerializedName("relatives") val relatives:String
)
data class UrlImage(
    @SerializedName("url") val url:String
)

