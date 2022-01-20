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