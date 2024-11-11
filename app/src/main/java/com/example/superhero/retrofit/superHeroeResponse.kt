package com.example.superhero.retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class SuperHeroeResponse (

    @SerializedName("results") val listHero: List<DataHero>,
    @SerializedName("results-for")val filteredName: String,
    @SerializedName("response") val success: Boolean
)
data class DataHero(
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
    @SerializedName("intelligence") val intelligence:Int,
    @SerializedName("strength") val strength:Int,
    @SerializedName("speed") val speed:Int,
    @SerializedName("durability") val durability:Int,
    @SerializedName("power") val power:Int,
    @SerializedName("combat") val combat:Int
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
    @SerializedName("height") val height:List<Float>,
    @SerializedName("weight") val weight:List<Float>,
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

