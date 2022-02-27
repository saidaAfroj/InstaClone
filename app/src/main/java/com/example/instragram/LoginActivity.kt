package com.example.instragram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Need to check if the user is logged in already
        // if logged in then take them to MainActivity
        if( ParseUser.getCurrentUser()!= null){
           gotoMainActivity()
       }

        findViewById<Button>(R.id.login_button).setOnClickListener{
            var username = findViewById<EditText>(R.id.et_username).text.toString()
            var password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username,password)

    }
        findViewById<Button>(R.id.sign_Button).setOnClickListener{
            var username = findViewById<EditText>(R.id.et_username).text.toString()
            var password = findViewById<EditText>(R.id.et_password).text.toString()
            sighnupUser(username,password)

        }
    }
     private fun sighnupUser(username: String,password: String){
         val user = ParseUser()

// Set fields for the user to be created
         user.setUsername(username)
         user.setPassword(password)

         user.signUpInBackground { e ->
             if (e == null) {
                 // Hooray! Let them use the app now.
                 //now navigate the user to mainActivity
                 //show a toast msg to user that login sucess
                 Toast.makeText(this,"Sign Up Successful!NOw navigating to main Activity!",Toast.LENGTH_SHORT).show()
                 gotoMainActivity()
             } else {
                 e.printStackTrace()
                 //Show a Toast that user login not successful
                 Toast.makeText(this,"Sign Up is not Successful",Toast.LENGTH_SHORT).show()
                 // Sign up didn't succeed. Look at the ParseException
                 // to figure out what went wrong
             }
         }

     }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Log In success!!")
                gotoMainActivity()
                // Hooray!  The user is logged in.
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Error Logging in ",Toast.LENGTH_SHORT).show()
                // Signup failed.  Look at the ParseException to see what happened.
            }})
        )
    }

    private fun gotoMainActivity(){
        val intent = Intent(this@LoginActivity,MainActivity::class.java)// 1st parameter indicate that
        //from which page to go and 2nd parameter indicates that where to go . so agter successfully log in it will go to main
        //activity
        startActivity(intent)
        finish() // calling finish method will refresh the login page so when we go back to log in page we
        // will not see any username or password typed . so back activity will be destroyed.

    }
    companion object {
        const val TAG = "LoginActivity"
    }
}