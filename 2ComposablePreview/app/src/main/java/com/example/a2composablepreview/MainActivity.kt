package com.example.a2composablepreview

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.a2composablepreview.ui.theme._2ComposablePreviewTheme
import androidx.compose.foundation.layout.Column as Column1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            _2ComposablePreviewTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
            Column {
                MyTextView()
                MyTextView()
                MyTextView()
            }
            MyTextView()
        }
    }
}

// @Composable: 이 함수는 Compose UI를 그리는 함수
@Composable
fun MyTextView() {
    Text(text = "Hello 여러분!!!")
}

// @Preview: Android Studio의 디자인 미리보기 창에 보이도록 설정
// 아래 예시는 하나의 함수에 Preview를 2개 지정
@Preview(showBackground = true, name="Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, name="Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyTextViewPreview() {
    MyTextView()
}

//@Preview(showBackground = true)
//@Composable
//fun MyTextViewPreview2() {
//    MyTextView()
//}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    _2ComposablePreviewTheme {
//        Greeting("Android")
//    }
//}