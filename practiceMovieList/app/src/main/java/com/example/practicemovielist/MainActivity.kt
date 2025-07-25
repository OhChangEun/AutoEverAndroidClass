package com.example.practicemovielist

import android.R.attr.contentDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.practicemovielist.ui.theme.PracticeMovieListTheme

data class Movie(
    val title: String,
    val imageResId: Int,
    var isFavorite: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var movieList by remember {
                mutableStateOf(
                    listOf(
                        Movie("인셉션", R.drawable.poster_img),
                        Movie("인터스텔라", R.drawable.poster_img),
                        Movie("터넷", R.drawable.poster_img),
                    )
                )
            }
            var isFavorite by rememberSaveable { mutableStateOf(false) }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = { FooterBar() }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp)
//                            .background(Color.Red)
                ) {
//                    PosterCard(isFavorite) { favorite ->
//                        isFavorite = favorite
//                    }
                    LazyColumn (
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        itemsIndexed(movieList) { index, movie ->
                            PosterCard(
                                movie = movie,
                                onTabFavorite = { updatedFavorite ->
                                    // 상태 업데이트
                                    movieList = movieList.toMutableList().also {
                                        it[index] = it[index].copy(isFavorite=updatedFavorite)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
//fun PosterCard(isFavorite: Boolean, onTabFavorite: (Boolean) -> Unit) {
fun PosterCard(movie: Movie, onTabFavorite: (Boolean) -> Unit) {

        Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box() {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = movie.imageResId),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd

            ) {
                IconButton(
                    onClick = {
                        onTabFavorite(!movie.isFavorite)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(48.dp)
                    )
                }
            }
        }
    }
}

// 하단 푸터
@Composable
fun FooterBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* 왼쪽 액션 */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "설정",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(36.dp)
                )
            }
            IconButton(onClick = { /* 가운데 액션 */ }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "홈",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(36.dp)
                )
            }
            IconButton(onClick = { /* 오른쪽 액션 */ }) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "프로필",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

