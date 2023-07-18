package com.nytimes.firstzipline.lib.js

import app.cash.zipline.Zipline
import com.nytimes.firstzipline.lib.TheMathInterface

private val zipline by lazy { Zipline.get() }

@OptIn(ExperimentalJsExport::class)
@JsExport
fun launchZipline() {
    zipline.bind<TheMathInterface>(
        name = "theMathService",
        instance = JsMath()
    )
}

class JsMath : TheMathInterface {
    override fun math(a: Int, b: Int): Int {
        return a + b
    }
}
