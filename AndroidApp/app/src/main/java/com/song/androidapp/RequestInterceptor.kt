package com.song.androidapp

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

// 请求拦截器：请求前拦截
class RequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        // 打印请求信息，或者修改请求头等
        println("Request URL: ${originalRequest.url}")
        println("Request Headers: ${originalRequest.headers}")

        // 在请求头中添加自定义信息
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer your_token_here") // 示例：添加 Authorization 头
            .build()

        return chain.proceed(newRequest)
    }
}

// 响应拦截器：响应后拦截
class ResponseInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        // 处理响应数据，打印响应内容
        println("Response Code: ${response.code}")
        println("Response Body: ${response.body?.string()}")

        // 可以根据需要修改响应内容，例如缓存或错误处理
        return response
    }
}

// OkHttpClient 单例：集成拦截器、日志等
object OkHttpClientSingleton {
    val client: OkHttpClient = OkHttpClient.Builder()
        // 添加请求拦截器
        .addInterceptor(RequestInterceptor())
        // 添加响应拦截器
        .addInterceptor(ResponseInterceptor())
        // 可选：添加日志拦截器，用于打印请求和响应日志
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}

// 封装 GET 请求方法
fun getRequest(url: String) {
    val request = Request.Builder()
        .url(url)
        .build()

    // 使用 OkHttpClient 发送 GET 请求
    OkHttpClientSingleton.client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 请求失败处理
            println("GET request failed: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                // 请求成功，获取响应
                val responseBody = response.body?.string()
                println("GET Response: $responseBody")
            } else {
                println("GET request failed with code: ${response.code}")
            }
        }
    })
}

// 封装 POST 请求方法（接收 JSON 对象）
fun postRequest(url: String, json: String) {
    val mediaType = "application/json".toMediaTypeOrNull()
    val requestBody = RequestBody.create(mediaType, json)

    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    // 使用 OkHttpClient 发送 POST 请求
    OkHttpClientSingleton.client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // 请求失败处理
            println("POST request failed: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                // 请求成功，获取响应
                val responseBody = response.body
                println("POST Response: $responseBody")
            } else {
                println("POST request failed with code: ${response.code}")
            }
        }
    })
}

