package com.example.selogu.api

import com.example.selogu.data.model.DetailUserResponse
import com.example.selogu.data.model.User
import com.example.selogu.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET(/* value = */ "search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ) : Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail (
        @Path("username") username: String
    ) : Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ) : Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ) : Call<ArrayList<User>>
}