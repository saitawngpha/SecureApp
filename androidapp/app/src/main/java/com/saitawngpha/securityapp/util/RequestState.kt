package com.saitawngpha.securityapp.util

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */


sealed class RequestState<out T> {
    data object Idel: RequestState<Nothing>()
    data object Loading: RequestState<Nothing>()
    data class Success<T>(val data: T): RequestState<T>()
    data class Error(val message: String): RequestState<Nothing>() {
        fun parseError(): String {
            return if(message.contains("failed to connect")) "Failed to connect to server!"
            else if(message.contains("timeout")) "Connection timeout, please try again later!"
            else message
        }
    }

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error
}