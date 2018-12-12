package com.gmail.pmanenok.data.net

import com.google.gson.Gson

class RestErrorParser(gson: Gson) {
    /*fun <T> parseError(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { observable ->
            observable.onErrorReturn { throwable ->
                when (throwable) {
                    is HttpException -> {
                        var errorBody = throwable.response().errorBody()?.string()
                        if (errorBody == null) {
                            throw AppException(AppErrorType.UNKNOWN)
                        }
                        try {
                            val errorObject = gson.fromJson<ErrorResponse>(errorBody, ErrorResponse::class.java)
                            when (errorObject.errorCode) {
                                123 -> {
                                    throw AppException(AppErrorType.BLOCKED)
                                }
                                234 -> {
                                    throw AppException(AppErrorType.WRONG_ID)
                                }
                                else -> {
                                    throw AppException(AppErrorType.UNKNOWN)
                                }
                            }
                        } catch (e: Exception) {
//                            Log.e("parser", e.message)
                            throw AppException(AppErrorType.PARSER_ERROR)
                        }
                        //throw AppException(AppErrorType.UNKNOWN)
                    }
                    is SocketTimeoutException -> {
                        throw AppException(AppErrorType.SERVER_IS_NOT_AVAILABLE)
                    }
                    else -> {
                        throw AppException(AppErrorType.UNKNOWN)
                    }
                }

            }
        }
    }*/
}