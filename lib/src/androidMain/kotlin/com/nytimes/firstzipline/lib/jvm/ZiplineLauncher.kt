package com.nytimes.firstzipline.lib.jvm

import app.cash.zipline.Zipline
import app.cash.zipline.loader.LoadResult
import app.cash.zipline.loader.ManifestVerifier
import app.cash.zipline.loader.ZiplineLoader
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

suspend fun launchZipline(dispatcher: CoroutineDispatcher): Zipline {
    val manifestUrl = "http://10.0.2.2:8080/manifest.zipline.json"
    val loader = ZiplineLoader(
        dispatcher,
        ManifestVerifier.NO_SIGNATURE_CHECKS,
        OkHttpClient(),
    )
    return when (val result = loader.loadOnce("trivia", manifestUrl)) {
        is LoadResult.Success -> result.zipline
        is LoadResult.Failure -> error(result.exception)
    }
}

