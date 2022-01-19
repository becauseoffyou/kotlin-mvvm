package api

import data.model.User
import data.model.UserDetail
import data.model.UserResponse
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

//    @GET("users/{username}/repos")
//    @Headers("Authorization: token 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")
//    fun ambilRepoUser(
//        @Query(value = "username") username: String
//    ): Call<ArrayList<User>>
}