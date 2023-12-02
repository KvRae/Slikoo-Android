package slikoo.kvrae.slikoo.data.datasources.remote

import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.FeedBack

class FeedbackRemoteDataSource {

    suspend fun getFeedbacks(token: String, id: Int): List<FeedBack> {
        try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .getMyFeedbacks("Bearer $token", id)
            if (response.isSuccessful) {
                response.body()?.feedbacks?.let {
                    return it
                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}