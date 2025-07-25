package com.example.a1columnrowtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a1columnrowtest.ui.theme._1ColumnRowTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            _1ColumnRowTestTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
            Row {
                Text("Hello")
                Text("World") // 단축키: 커맨드 + backspace
                Text("Hello") // 단축키: 커맨드 + D
            }


            Scaffold { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color.LightGray)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.spacedBy(16.dp),
//                    verticalArrangement = Arrangement.SpaceBetween,
//                    verticalArrangement = Arrangement.SpaceAround,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        "Hello0",
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )
                    )
                    Text("Hello1")
                    Text("Hello2")
//                    Text(
//                        "Hello",
//                        modifier = Modifier.padding(end = 16.dp)
//                    ) // top, bottom, start, end 로 상하좌우 여백 가능
//
//                    Button(onClick = {/*TODO */ }) { }
//
//                    Text("Hello")
//
//                    // 역할을 안하는 위젯 (여백 주기)
//                    Spacer(
//                        modifier = Modifier.height(32.dp)
//                    )
//                    Text("Hello")
//                    Text("Hello")
                }
            }

        }
    }
}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!!!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    _1ColumnRowTestTheme {
//        Greeting("Android")
//    }
//}