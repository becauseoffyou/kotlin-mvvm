package api

import data.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET( "search/users?")
    @Headers("Authorization: token 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")
    fun ambilDataUser(
        @Query(value = "q") query: String
        ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun ambilDetailUser(
        @Path(value = "username") username: String
    ): Call<UserDetail>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun ambilRepoUser(
        @Path(value = "username") username: String
    ): Call<ArrayList<UserRepo>>
}