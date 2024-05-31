package ru.polisha.zhest

import android.util.Log
import ru.polisha.zhest.MainActivity.Companion.BASE_TAG
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.request.*
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.util.InternalAPI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
//import kotlinx.io.streams.asInput
import java.io.ByteArrayInputStream


private const val BASE_URL = "https://httpbin.org"
private const val GET_UUID = "$BASE_URL/uuid"
private const val POST_TEST = "$BASE_URL/post"
private const val GET_TEST = "$BASE_URL/get"




//@OptIn(DelicateCoroutinesApi::class)
fun performAllCases() {

    val exHandler = CoroutineExceptionHandler { _, th ->
        Log.wtf(BASE_TAG, th)
    }

    GlobalScope.launch(Dispatchers.IO + exHandler) {
        val client = HttpClient()
//        simpleCase(client)
//        bytesCase(client)
//        closableSimpleCase()
//
//        postHeadersCase(client)
//        rawPostHeadersCase(client)
//        typedRawPostHeadersCase(client)
//        submitFormCase(client)
//        submitFormBinaryCase(client)


        client.close()
    }
}

//suspend fun simpleCase(client: HttpClient) {
//    val data = client.get<String>(GET_UUID)
//    Log.i("$BASE_TAG Simple case", data)
//}
//
//private fun <T> HttpClient.get(getUuid: T): T {
//    TODO("Not yet implemented")
//}
//
//suspend fun bytesCase(client: HttpClient) {
//    val data = client.call(GET_UUID).response.readBytes()
//    Log.i("$BASE_TAG Bytes case", data.joinToString(" ", "[", "]") { it.toString(16).toUpperCase() })
//}
//
//suspend fun closableSimpleCase() {
//    HttpClient().use {
//        val data: String = it.get(GET_UUID)
//        Log.i("$BASE_TAG Closable case", data)
//    }
//}
//
//suspend fun postHeadersCase(client: HttpClient) {
//    val data: HttpResponse = client.post(POST_TEST) {
//        fillHeadersCaseParameters()
//    }
//    Log.i("$BASE_TAG Post case", data.toString())
//}
//
//suspend fun rawPostHeadersCase(client: HttpClient) {
//    val data: String = client.call {
//        url.takeFrom(POST_TEST)
//        method = HttpMethod.Post
////        fillHeadersCaseParameters()
//    }
//        .response
//        .readText()
//
//    Log.i("$BASE_TAG Raw post case", data)
//}
//
//suspend fun typedRawPostHeadersCase(client: HttpClient) {
//    val data = client.request<String>() {
//        url.takeFrom(POST_TEST)
//        method = HttpMethod.Post
//        fillHeadersCaseParameters()
//    }
//    Log.i("$BASE_TAG Typed raw post", data)
//}

@OptIn(InternalAPI::class)
private fun HttpRequestBuilder.fillHeadersCaseParameters() {
    parameter("name", "Andrei") // добавляем отдельный параметр в строку запроса

    url.parameters.appendAll(
        parametersOf(
            "ducks" to listOf("White duck", "Grey duck"), // добавляем список параметров в строку запроса
            "fish" to listOf("Goldfish") // добавляем отдельный параметр в строку запроса
        )
    )

    header("Ktor", "https://ktor.io") // добавляем отдельный заголовок

    headers /* получаем доступ к билдеру списка заголовков */ {
        append("Kotlin", "https://kotl.in")
    }

    headers.append("Planet", "Mars") // добавляем заголовок
    headers.appendMissing("Planet", listOf("Mars", "Earth")) // добавляем только новые заголовки, "Mars" будет пропущен
    headers.appendAll("Pilot", listOf("Starman"))  // ещё вариант добаления заголовка

    body = FormDataContent( // создаем параметры, которые будут переданы как form data
        Parameters.build {
            append("Low-level", "C")
            append("High-level", "Java")
        }
    )
}

//suspend fun submitFormCase(client: HttpClient) {
//
//    val params = Parameters.build {
//        append("Star", "Sun")
//        append("Planet", "Mercury")
//    }
//
//    val getData: HttpResponse = client.submitForm(GET_TEST, params, encodeInQuery = true) // параметры в строке запроса
//    val postData: HttpResponse = client.submitForm(POST_TEST, params, encodeInQuery = false) // параметры в form
//
//    Log.i("$BASE_TAG Submit form get", getData.toString())
//    Log.i("$BASE_TAG Submit form post", postData.toString())
//}

//suspend fun submitFormBinaryCase(client: HttpClient) {
//
//    val inputStream = ByteArrayInputStream(byteArrayOf(77, 78, 79))
//
//    val formData = formData {
//        append("String value", "My name is") // строковый параметр
//        append("Number value", 179) // числовой
//        append("Bytes value", byteArrayOf(12, 74, 98)) // набор байт
//        append(
//            "Input value",
//            inputStream.asInput(),
//            headersOf("Stream header", "Stream header value")
//        ) // поток и заголовки
//    }
//
//    val data: HttpResponse = client.submitFormWithBinaryData(POST_TEST, formData)
//    Log.i("$BASE_TAG Submit binary case", data.toString())
//}