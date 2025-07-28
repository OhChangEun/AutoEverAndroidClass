package com.example.viewmodel

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    // val viewModel = MainViewModel(); // 외부에 viewmodel 선언해서 recomposition 발생해도 상태 안변함, 하지만 예전 방식,
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            var text: MutableState<String> = remember { mutableStateOf("Hello") }

//            val viewModel:MainViewModel = viewModel<MainViewModel>()
            val viewModel: MainViewModel = viewModel()
            // 이 코드는 androidx.lifecycle.viewmodel.compose.viewModel() 함수를 호출하여,
            // 현재 Composable의 LifecycleOwner(예: Activity or Fragment)에 연결된 ViewModel 인스턴스를 가져옵니다.
            // 타입 추론 덕분에 MainViewModel을 자동으로 생성하거나, 이미 있으면 재사용합니다.
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(viewModel.text.value)
                Button(
                    onClick = {
                        viewModel.changeText("World222")
                    },
                ) {
                    Text("변경")
                }
            }
        }
    }
}

class MainViewModel : ViewModel() {
    // mutableStateOf("Hello") → Compose 상태 시스템과 연결되는 상태 선언
    // _text는 내부 상태, text는 외부에서 읽기 전용으로 노출
    private val _text: MutableState<String> = mutableStateOf("Hello")
    val text: State<String> = _text

    fun changeText(newText: String) {
        _text.value = newText
    }
}