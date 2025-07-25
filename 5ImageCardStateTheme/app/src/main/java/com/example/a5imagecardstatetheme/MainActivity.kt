package com.example.a5imagecardstatetheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a5imagecardstatetheme.ui.theme._5ImageCardStateThemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isFavorite by rememberSaveable { mutableStateOf(false) }

            _5ImageCardStateThemeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        PosterCard(isFavorite) { favorite ->
                            isFavorite = favorite
                        }
                    }
                }
            }
        }
    }
}

@Composable
// isFavorite: Boolean
// → 단순히 불리언 값을 전달받는 변수 (state)
//
//onTabFavorite: (Boolean) -> Unit
// → Boolean을 받아서 아무것도 반환하지 않는 함수 타입
//즉, Boolean 하나를 인자로 받는 함수이고, 그 함수는 Unit(void) 을 반환
fun PosterCard(isFavorite: Boolean, onTabFavorite: (Boolean) -> Unit) {
    // Text(text = "PosterCard")

    // var isFavorite = remember { mutableStateOf(false) }
    // 화면이 회전되면 다시 렌더링 되서 위 코드가 다시 실행되어서 false가 나타남
    // >> 아래 코드를 쓰자

    // var isFavorite by rememberSaveable { mutableStateOf(false) }
    // 외부로 빼서 쓰자

    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(300.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box() {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.poster_img),
                contentDescription = "Poster", // 눈이 보이지 않는 사람에게 알려주는 용도
                contentScale = ContentScale.Crop
            )
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = {
                        onTabFavorite(!isFavorite);
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