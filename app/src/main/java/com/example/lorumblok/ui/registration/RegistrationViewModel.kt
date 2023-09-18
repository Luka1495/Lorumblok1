package com.example.lorumblok.ui.registration

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lorumblok.database.UserRepository
import com.example.lorumblok.ui.NavRoutes
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userRepository: UserRepository= UserRepository()):ViewModel() {

    private var _isUserNameUnique = mutableStateOf(false)
    var isLoginError= mutableStateOf(false)

    /*fun isUsernameValid(username: String,context: Context){
        viewModelScope.launch {
            _isUserNameUnique.value = !userRepository.validateUserName(username, context)
        }
    }*/


    fun insertUser(username:String,password:String,navController: NavController){
        viewModelScope.launch {
            if(Patterns.EMAIL_ADDRESS.matcher(username).matches() && password.length>5){
                if(userRepository.registerUser(username, password))
                navigateToStandingsScreen(navController)
            }else{
                isLoginError.value=true
            }

        }
    }
    private fun navigateToStandingsScreen(navController:NavController){
        navController.navigate(NavRoutes.STANDINGS.route){
            popUpTo(NavRoutes.REGISTRATION.route){
                inclusive=true
            }
        }
    }
}