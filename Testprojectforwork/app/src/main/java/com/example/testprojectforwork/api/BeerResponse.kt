package com.example.testprojectforwork.api

import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("tagline")
    var tagline: String? = null,
    @SerializedName("first_brewed")
    var firstBrewed: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("image_url")
    var imageURL: String? = null,
    @SerializedName("abv")
    var abv: Double? = null,
//    @SerializedName("ibu")
//    var ibu: Long? = null,
//    @SerializedName("target_fg")
//    var targetFg: Long? = null,
//    @SerializedName("targe_og")
//    var targetOg: Long? = null,
//    @SerializedName("ebc")
//    var ebc: Long? = null,
//    @SerializedName("srm")
//    var srm: Long? = null,
//    @SerializedName("ph")
//    var ph: Double? = null,
//    @SerializedName("attenuation_level")
//    var attenuationLevel: Long? = null,
    @SerializedName("volume")
    var volume: BoilVolume? = null
//    @SerializedName("boilVolume")
//    var boilVolume: BoilVolume? = null,
//    @SerializedName("method")
//    var method: Method? = null,
//    @SerializedName("ingredients")
//    var ingredients: Ingredients? = null,
//    @SerializedName("foodPairing")
//    var foodPairing: List<String>? = null,
//    @SerializedName("brewersTips")
//    var brewersTips: String? = null,
//    @SerializedName("contributedBy")
//    var contributedBy: String? = null

)

data class BoilVolume(
    @SerializedName("value")
    var value: Double,
    @SerializedName("unit")
    var unit: String
)

data class Ingredients(
    @SerializedName("malt")
    val malt: List<Malt>,
    @SerializedName("hops")
    val hops: List<Hop>,
    @SerializedName("yeast")
    val yeast: String
)

data class Hop(
    @SerializedName("name")
    var name: String,
    @SerializedName("amount")
    var amount: BoilVolume,
    @SerializedName("add")
    var add: String,
    @SerializedName("attribute")
    var attribute: String
)

data class Malt(
    @SerializedName("name")
    var name: String,
    @SerializedName("amount")
    var amount: BoilVolume
)

data class Method(
    @SerializedName("mashTemp")
    var mashTemp: List<MashTemp>,
    @SerializedName("fermentation")
    var fermentation: Fermentation,
    @SerializedName("twist")
    var twist: Any? = null
)

data class Fermentation(
    @SerializedName("temp")
    var temp: BoilVolume
)

data class MashTemp(
    @SerializedName("temp")
    var temp: BoilVolume,
    @SerializedName("duration")
    var duration: Long
)