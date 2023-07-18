package com.nytimes.firstziplineconsumer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.cash.zipline.Zipline
import com.nytimes.firstzipline.lib.TheMathInterface
import com.nytimes.firstzipline.lib.jvm.launchZipline
import com.nytimes.firstziplineconsumer.ui.theme.FirstZiplineConsumerTheme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val ziplineExecutor: ExecutorService by lazy {
    Executors.newFixedThreadPool(1) {
        Thread(it, "Zipline")
    }
}

val ziplineDispatcher: CoroutineDispatcher by lazy {
    ziplineExecutor.asCoroutineDispatcher()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            runBlocking {
                val zipline: Zipline = launchZipline(ziplineDispatcher)
                Log.d("MATH", "Zipline loaded")
                val math: TheMathInterface = zipline
                    .take<TheMathInterface>("theMathService")
                Log.d("MATH", "Service loaded")
                val mathResult = math.math(3, 4)
                Log.d("MATH", "Result: $mathResult")
            }

            FirstZiplineConsumerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")


                }
            }
        }
    }
}

fun test(math: TheMathInterface) {

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstZiplineConsumerTheme {
        Greeting("Android")
    }
}