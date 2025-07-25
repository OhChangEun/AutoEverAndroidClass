package com.example.practice

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.ui.theme.PracticeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isFavorite by rememberSaveable { mutableStateOf(false) }
            var favoriteStates by rememberSaveable { mutableStateOf(List(10) {false})}
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .fillMaxSize(),
                ) {
                    //MainPage()
                    PosterCard(isFavorite) { favorite ->
                        isFavorite = favorite
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(isFavorite: Boolean, onTabFavorite: (Boolean) -> Unit, modifier: Modifier = Modifier)  {
    Card(
        modifier = modifier
            .height(300.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ){
        Box() {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.poster_img),
                contentDescription = "Poster",
                contentScale = ContentScale.Crop
            )
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = {
                        onTabFavorite(!isFavorite)
                    }
                ) {
                    Icon (
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun PosterCard(isFavorite: Boolean, onTabFavorite: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        MovieCard(isFavorite, onTabFavorite, modifier = Modifier.weight(1f))
        MovieCard(isFavorite, onTabFavorite, modifier = Modifier.weight(2f))
        MovieCard(isFavorite, onTabFavorite, modifier = Modifier.weight(4f))

    }
}

//@Composable
//fun MainPage() {
//    // for문과 scroll 사용
//    val scrollState = rememberScrollState()
//    Column (
//        modifier = Modifier
//            .background(Color.Green)
//            .fillMaxSize()
//            .verticalScroll(scrollState)
//    ) {
//        for (i in 1 .. 50) {
//            Text(text = "글씨 $i")
//        }
//    }
//    // lazy column 사용
//    LazyColumn (
//        modifier = Modifier
//            .background(Color.Green)
//            .fillMaxSize(),
//        contentPadding = PaddingValues(16.dp)
//    ) {
//        item {
//            Text(text="Header")
//        }
//        items(50) { index ->
//            MyRow(index)
//        }
//        item {
//            Text(text="Footer")
//        }
//    }
//}
//
//@Composable
//fun MyRow(index: Int) {
//    Row {
//        Text(text = "글씨 ${index + 1}")
//    }
//}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
// 1. Column, Row, Text
// 새로운 프로젝트 만들기
// 스케폴드 구현
// 스케폴드 모디파이어로 화면 채우기
// 스케폴드 안에 컬럼 배치
// 텍스트 두 개 배치
// 컬럼 모디파이어(화면 채우기, 백그라운드 색상 변경, 이너패딩, 자체 패딩)
// 컬럼 정렬(가로 가운데 정렬, 세로 항목 간 최디 간격)
//            Scaffold { innerPadding ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Blue)
//                        .padding(innerPadding)
//                        .padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.SpaceBetween,
//                ) {
//                    Text("Hello",
//                        style = TextStyle(
//                            fontSize = 40.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White
//                        )
//                    )
//                    Text("Bye",
//                        style = TextStyle(
//                            fontSize = 40.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.White
//                        )
//                    )
//                }


// 3.
//            Scaffold { innerPadding ->
//                Box(
//                    modifier = Modifier
//                        .padding(innerPadding)
//                        .fillMaxWidth()
//                        .height(200.dp)
//                        .background(Color.Green)
//                ) {
//                    Text("Hello", style = TextStyle(
//                        fontSize = 32.sp,
//                        color = Color.White
//                    ))
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        contentAlignment = Alignment.BottomEnd
//                    ) {
//                        Text("1122112",
//                            style = TextStyle(
//                                fontSize = 32.sp,
//                                color = Color.White
//                            )
//                        )
//                    }
//                }
//            }
//}
//}
//}

// 2. 컴포저블, 프리뷰
// 컴포저블과 프리뷰를 삭제한 후 직접 만들어본다.
// 컴포저블의 동일한 프리뷰 하나 더 만들기
// "프리뷰 함수 2개인 방식"과 "어노테이션"방식으로

//@Composable
//fun MyTextView() {
//    Text(text = "Hello 오창은!!!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MyTextViewPreview1() {
//    MyTextView()
//}
//@Preview(showBackground = true)
//@Composable
//fun MyTextViewPreview2() {
//    MyTextView()
//}
//
//@Preview(showBackground=true, name="Light")
//@Preview(showBackground=true, name="Dark")
//@Composable
//fun MyTextViewPreview() {
//    MyTextView()
//}
