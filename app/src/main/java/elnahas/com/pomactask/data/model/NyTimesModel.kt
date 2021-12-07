package elnahas.com.pomactask.data.model


import com.google.gson.annotations.SerializedName

data class NyTimesModel(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val resultModels: List<ResultModel>,
    @SerializedName("status")
    val status: String
)