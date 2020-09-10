package com.joana.achou.gads2020leaderboard.retrofit

import com.joana.achou.gads2020leaderboard.models.SkillIqModel
import com.joana.achou.gads2020leaderboard.models.TopLearnerModel
import retrofit2.Call
import retrofit2.http.*

interface ApiUrls {

    @GET("/api/hours")
    fun getTopLearners(): Call<List<TopLearnerModel>>

    @GET("/api/skilliq")
    fun getTopScorers(): Call<List<SkillIqModel>>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submitProject(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") email: String,
        @Field("entry.284483984") link: String
    ): Call<Void>
}