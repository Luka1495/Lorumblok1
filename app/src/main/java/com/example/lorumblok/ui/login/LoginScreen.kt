package com.example.lorumblok.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lorumblok.ui.NavRoutes
import com.example.lorumblok.ui.theme.LorumBlokTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context= LocalContext.current
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val loginViewModel:LoginViewModel= viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Email", color = MaterialTheme.colorScheme.onPrimary) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                errorBorderColor = MaterialTheme.colorScheme.error,
                textColor = MaterialTheme.colorScheme.onPrimary
            ),
            isError = !loginViewModel.isFieldValid.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password", color = MaterialTheme.colorScheme.onPrimary) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                errorBorderColor = MaterialTheme.colorScheme.error,
                textColor = MaterialTheme.colorScheme.onPrimary
            ),
            isError = !loginViewModel.isFieldValid.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick =
            {
                loginViewModel.validateUser(username,password, navController)
                if(loginViewModel.isLoginSuccess.value){
                    navController.navigate(NavRoutes.STANDINGS.route){
                        popUpTo(NavRoutes.LOGIN.route){
                            inclusive=true
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Log In")
        }
        Text(
            text = "Don't have an account?",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = {navController.navigate(NavRoutes.REGISTRATION.route)},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Register")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "E-Mail", color = MaterialTheme.colorScheme.onPrimary) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                errorBorderColor = MaterialTheme.colorScheme.error,
                textColor = MaterialTheme.colorScheme.onPrimary
            ),
            isError = false
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password", color = MaterialTheme.colorScheme.onPrimary) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                errorBorderColor = MaterialTheme.colorScheme.error,
                textColor = MaterialTheme.colorScheme.onPrimary
            ),
            isError = false
        )
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Log In")
        }
        Text(
            text = "Don't have an account?",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 14.sp,
        )
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Register")
        }

    }

}

@Preview
@Composable
private fun PreviewLoginScreenContent() {
    LorumBlokTheme {
        LoginScreenContent()
    }
}