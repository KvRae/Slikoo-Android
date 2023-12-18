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

    suspend fun submitFeedback(
        feedback: Feedback,
        token: String
    ): Int {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .makeFeedback(
                    body = feedback,
                    token = token
                )
            if (response.isSuccessful) {
                response.body()?.code ?: 400
            } else {
                400
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            500
        }
    }

}