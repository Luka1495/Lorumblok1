package com.example.lorumblok.ui.login

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lorumblok.database.UserRepository
import com.example.lorumblok.ui.NavRoutes
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository= UserRepository()):ViewModel() {
    var isLoginSuccess = mutableStateOf(false)
    var isFieldValid = mutableStateOf(true)

    fun validateUser(username:String,password: String,navController: NavController){
        viewModelScope.launch {
            if(Patterns.EMAIL_ADDRESS.matcher(username).matches() && password.length>5){
                if(userRepository.loginUser(username, password)){
                    navigateToStandingsScreen(navController)
                }
            }else{
                isFieldValid.value=false
            }

        }
    }

    private fun navigateToStandingsScreen(navController: NavController){
        navController.navigate(NavRoutes.STANDINGS.route){
            popUpTo(NavRoutes.LOGIN.route){
                inclusive=true
            }
        }
    }

}