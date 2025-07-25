package com.example.a6textfield

import android.R.attr.label
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a6textfield.ui.theme._6TextFieldTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            var textValue: MutableState<String> = remember { mutableStateOf("") }
            // **mutable(뮤터블)**은 영어로 "변경 가능한", "수정할 수 있는"
            // MutableState<String> 형태로 반환해서 .value로 접근해야함
            // 구조 분해를 할 수 있다.
            var (text, setText) = remember { mutableStateOf("") }
            val snackbarHostState = remember { SnackbarHostState() }
            // snackbarHostState: 스낵바를 띄우기 위한 상태 객체
            val scope = rememberCoroutineScope()
            // rememberCoroutineScope(): 코루틴 사용 (Snackbar는 suspend 함수로 비동기 처리 필요)

            val keyboardController = LocalSoftwareKeyboardController.current // 소프트 키보드 제어 객체

            // var textValue by remember { mutableStateOf("") }
            // 이렇게 쓰면 Delegates를 이용해서 .value 없이 바로 접근할 수 있습니다.

            _6TextFieldTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                    // snackbarHost: snackbar 출력 영역 등록
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        TextField(
                            value = text,
                            onValueChange = setText,
                            label = { Text("입력하세요") }
                            // 둘 다 같은 상태값인 text를 사용함 (내용 동기화됨)
                            // 입력하면 setText(...)로 상태 업데이트
                        )
                        OutlinedTextField(
                            label = { Text(text = "성명") },
                            placeholder = { Text(text = "이름을 입력하세요.") },
                            value = text,
                            onValueChange = setText
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                keyboardController?.hide()
                                scope.launch {
                                    snackbarHostState.showSnackbar("안녕하세요, $text 님")
                                }
                                setText("")
                            },
                        ) {
                            Text(text = "클릭!!")
                        }
                    }
                }
            }
        }
    }
}