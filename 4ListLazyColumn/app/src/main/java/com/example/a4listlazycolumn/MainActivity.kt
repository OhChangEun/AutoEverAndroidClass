package com.example.a4listlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a4listlazycolumn.ui.theme._4ListLazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _4ListLazyColumnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.Red)
                            .fillMaxSize()
                    ) {
                        MainPage()
                    }
                }
            }
        }
    }
}

// 글자 바꾸기 fn + shift +  f6


// 메모리에 제일 처음 다 탑재하고, 좀 불합리한 선택임 >> LazyColumn을 사용하자
// 레이지 컬럼 사용시 스크롤 스테이트 필요없음
@Composable
fun MainPage() {

//    val scrollState:ScrollState = rememberScrollState()
//    Column (
//        modifier = Modifier
//            .background(Color.Green)
//            .fillMaxSize()
//            .verticalScroll(scrollState)
//    ){
//        for (i in 1 .. 50) {
//            Text(text = "Hello MainPage $i")
//        }
//    }

    LazyColumn (
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ){
        // 이거처럼 header 지정 가능, 반복아님
        item {
            Text(text="Header")
        }

        items(50) { index ->
            // 여기에 인스타그램처러 원하는 컴포저블들을 넣을 수 있음
            // 현재는 글자만
//            Text(text = "Hello MainPage $index")
            MyRow()
        }

        // 이거처럼 footer도 지정 가능
        item {
            Text(text="Footer")
        }
        item {
            Text(text="Footer2")
        }
    }
}

@Composable
fun MyRow() {
    Row {
        Text(text = "이것은")
        Spacer(modifier = Modifier.width(32.dp))
        Text(text = "로우입니다.")
    }
}