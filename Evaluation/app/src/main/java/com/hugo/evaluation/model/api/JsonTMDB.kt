package com.hugo.evaluation.model.api

import com.google.gson.annotations.SerializedName
//Estructura del json que devuelve la pagina de peliculas
data class JsonTMDB(
    @SerializedName("page") var page: String,
    @SerializedName("total_pages") var total_pages: String,
    @SerializedName("results") var results: List<JsonMovie>,
    @SerializedName("total_results") var total_results: String
)