package com.saitawngpha.securityapp.domain

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */
interface KeyProviderServices {
    @Headers("Content-Type: application/json")
    @POST("api/provide")
    suspend fun getEncryptedApiKey(@Body publicKey: String) : Response<String>
}