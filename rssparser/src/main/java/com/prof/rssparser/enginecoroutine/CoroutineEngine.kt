package com.prof.rssparser.enginecoroutine

import com.prof.rssparser.Channel
import com.prof.rssparser.core.CoreXMLFetcher
import com.prof.rssparser.core.CoreXMLParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import java.nio.charset.Charset

internal object CoroutineEngine {

    suspend fun fetchXML(
        url: String,
        callFactory: Call.Factory,
        charset: Charset,
    ): String = withContext(Dispatchers.IO) {
        return@withContext CoreXMLFetcher.fetchXML(url, callFactory, charset)
    }

    suspend fun parseXML(xml: String): Channel = withContext(Dispatchers.IO) {
        return@withContext CoreXMLParser.parseXML(xml)
    }
}

