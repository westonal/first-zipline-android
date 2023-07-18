package com.nytimes.firstzipline.lib

import app.cash.zipline.ZiplineService

interface TheMathInterface : ZiplineService {
    fun math(a: Int, b: Int): Int
}
