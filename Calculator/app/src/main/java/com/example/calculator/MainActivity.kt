package com.example.calculator

import android.R.attr.label
import android.graphics.Color.blue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            val viewModel: CalculatorViewModel = viewModel()
            val inputText = viewModel.input.value;

            val buttons = listOf (
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
            )
            Scaffold() { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                ) {
                    Box (
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                    Text(
                        text = inputText,
                        fontSize = 48.sp,
                        maxLines = 1
                    )
                }

                    // 하단 영역
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(buttons) { label ->
                            Button(
                                onClick = {
                                    viewModel.onButtonClick(label)
                                },
                                shape = RoundedCornerShape(48.dp),
                                modifier = Modifier.height(90.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4CAF50),   // 초록색
                                    contentColor = Color.White           // 글자색
                                )
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 32.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

class CalculatorViewModel : ViewModel() {
    private var _display = mutableStateOf("0")
    val input: State<String> = _display

    private var inputA: String = ""
    private var inputB: String = ""
    private var operator: String? = null
    private var isOperatorPressed = false

    fun onButtonClick(label: String) {
        when (label) {
            "C" -> {
                _display.value = "0"
                inputA = ""
                inputB = ""
                operator = null
                isOperatorPressed = false
            }

            "=" -> {
                if (inputA.isNotEmpty() && inputB.isNotEmpty() && operator != null) {
                    val result = calculate(inputA, inputB, operator)
                    _display.value = result
                    inputA = result  // 결과를 다음 inputA로 사용
                    inputB = ""
                    operator = null
                    isOperatorPressed = false
                }
            }

            "+", "-", "*", "/" -> {
                if (_display.value.isNotEmpty() && !isOperatorPressed) {
                    inputA = _display.value
                    operator = label
                    _display.value += label
                    isOperatorPressed = true
                }
            }

            else -> { // 숫자 입력
                if (_display.value == "0"){
                    _display.value = label
                } else {
                    _display.value += label
                }

                if (!isOperatorPressed) {
                    inputA = _display.value
                } else {
                    inputB += label
                }
            }
        }
    }

    private fun calculate(op1: String, op2: String, operator: String?): String {
        return try {
            val a = op1.toDouble()
            val b = op2.toDouble()
            when (operator) {
                "+" -> (a + b).toString()
                "-" -> (a - b).toString()
                "*" -> (a * b).toString()
                "/" -> if (b != 0.0) (a / b).toString() else "Err"
                else -> "Err"
            }
        } catch (e: Exception) {
            "Err"
        }
    }
}