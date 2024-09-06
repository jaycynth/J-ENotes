package com.techne.jenotes.data.util

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

import com.squareup.moshi.Moshi
import com.squareup.moshi.JsonAdapter
import okhttp3.RequestBody
import javax.inject.Inject

class RequestHelper @Inject constructor( val moshi: Moshi) {

    inline fun <reified T> createRequestBody(req: T): RequestBody {
        val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        val json = adapter.toJson(req)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        return json.toRequestBody(mediaType)
    }
}

/*
Using inline and reified type parameters allows you to obtain the class type of the generic parameter T at runtime.
This avoids the need to pass the Class object manually.

fun <T> createRequestBody(req: T): RequestBody {
    val jsonElementReq = Gson().toJsonTree(req)
    val mediaType = "application/json; charset=utf-8".toMediaType()
    return jsonElementReq.toString().toRequestBody(mediaType)
}
 */