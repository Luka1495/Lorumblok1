package com.example.lorumblok.database

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRepository(private val auth:FirebaseAuth=Firebase.auth) {
    suspend fun loginUser(username:String, password:String):Boolean{
        var result:Boolean=false
        withContext(Dispatchers.IO){
            auth.signInWithEmailAndPassword(username,password).addOnCompleteListener{task->
                result=task.isSuccessful
            }.addOnFailureListener{
                result=false
            }.await()
        }
        return result
    }



    suspend fun registerUser(username: String, password: String):Boolean{
        var result=false
        withContext(Dispatchers.IO){
            auth.createUserWithEmailAndPassword(username,password).addOnCompleteListener { task->
                result=task.isSuccessful
            }.addOnFailureListener{
                result=false
            }.await()
        }
        return result
    }


    suspend fun logoutUser(){
        withContext(Dispatchers.IO){
            auth.signOut()
        }
    }





}