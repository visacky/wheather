package com.example.wheather.data.result

data class Result<out T>(
    var resultType: ResultTypes,
    val data: T? = null,
    val error: Error? = null
) {

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(ResultTypes.SUCCESS, data)
        }

        fun <T> error(error: Error): Result<T> {
            return Result(ResultTypes.ERROR, error = error)
        }
    }
}