package slikoo.kvrae.slikoo.data.datasources.remote


import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
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
        idMeal: Int,
        token: String
    ): Int {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .makeFeedback(
                    token = token,
                    idMeal = idMeal.toString().toRequestBody("plain/text".toMediaTypeOrNull()),
                    idReceiver = feedback.recipient.id.toString().toRequestBody("plain/text".toMediaTypeOrNull()),
                    idSender =  feedback.provider.id.toString().toRequestBody("plain/text".toMediaTypeOrNull()),
                    comment = feedback.comment.toRequestBody("plain/text".toMediaTypeOrNull()),
                    rate =  feedback.rate.toString().toRequestBody("plain/text".toMediaTypeOrNull()),
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

    suspend fun getMyReceivedFeedbacks(
        id: Int,
        token: String
    ): MutableList<Feedback> {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .getMyFeedbacks(
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