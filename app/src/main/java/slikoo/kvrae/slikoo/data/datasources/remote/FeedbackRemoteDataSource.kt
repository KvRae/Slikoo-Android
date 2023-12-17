package slikoo.kvrae.slikoo.data.datasources.remote

import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback

class FeedbackRemoteDataSource {

    suspend fun getMySubmittedFeedbacks(
        id: Int,
        token: String
    ): MutableList<Feedback> {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .getMySubmittedFeedbacks(
                    id = id,
                    token = token
                )
            if (response.isSuccessful) {
                response.body()?.feedbacks?.toMutableList() ?: mutableListOf()
            } else {
                mutableListOf()
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            mutableListOf()
        }
    }

}