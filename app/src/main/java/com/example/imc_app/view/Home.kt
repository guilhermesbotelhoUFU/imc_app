package com.example.imc_app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc_app.ui.theme.Blue
import com.example.imc_app.ui.theme.White
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.imc_app.datasource.Calculations
import com.example.imc_app.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(){

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }
    var textFieldError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Calculadora de IMC")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
    }
    ) {paddingValues ->  

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(
                color = White
            ).verticalScroll(rememberScrollState())
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Altura (cm)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp, 100.dp, 0.dp, 0.dp)
                )

                Text(
                    text = "Peso (kg)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp, 100.dp, 20.dp, 0.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                OutlinedTextField(
                    value = height,
                    onValueChange = {
                        if (it.length <= 3){
                            height = it
                        }
                    },
                    label = { Text(text = "Ex: 165") },
                    modifier = Modifier.fillMaxWidth(0.4f).padding(20.dp, 0.dp, 0.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        focusedLabelColor = Blue,
                        unfocusedLabelColor = Blue,
                        cursorColor = Blue,
                        errorIndicatorColor = Red,
                        errorContainerColor = White
                    ),
                    isError = textFieldError
                )

                OutlinedTextField(
                    value = weight,
                    onValueChange = {
                        if (it.length <= 7){
                            weight = it
                        }
                    },
                    label = { Text(text = "Ex: 70.50") },
                    modifier = Modifier.fillMaxWidth(0.7f).padding(20.dp, 0.dp, 20.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        focusedLabelColor = Blue,
                        unfocusedLabelColor = Blue,
                        cursorColor = Blue,
                        errorIndicatorColor = Red,
                        errorContainerColor = White
                    ),
                    isError = textFieldError
                )
            }

            Button(
                onClick = {
                    Calculations.calculateIMC(height = height, weight = weight, response = { result, textFieldState ->
                        resultMessage = result
                        textFieldError = textFieldState
                    })
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(50.dp),

            ) {
                Text(
                    text = "CALCULAR",
                    fontSize = 18.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = resultMessage,
                fontSize = 18.sp,
                color = Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomePreview(){
    Home()
}