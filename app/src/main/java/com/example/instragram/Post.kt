package com.example.instragram

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

//This class should be associated with the database table we have created in back4app site .
//Every post object has 3 things :
// Description : String
//  Images : FIle
//User : User
@ParseClassName("Post")
class Post : ParseObject(){
   //Following funtion wil return the keys form table post in back4app . Keys are the name of the coloumn in
    //the post table
    fun getDescription() : String? {
        return getString(KEY_Description)

    }
    fun setDescription(description:String){
        put(KEY_Description,description)
    }

    fun  getImage ():ParseFile?{
        return getParseFile(KEY_IMAGE)
    }
    fun setImage (parsefile : ParseFile){
        put(KEY_IMAGE,parsefile)
    }
    fun getUser():ParseUser?{
        return getParseUser(KEY_USER)
    }

    fun setUser(user : ParseUser){
        put(KEY_USER,user)
    }
    fun geturl():ParseUser?{
        return getParseUser(KEY_IMAGE)
    }
    fun seturl(user : ParseUser){
        put(KEY_IMAGE,user)
    }

    companion object {
        const val KEY_Description = "description"
        const val KEY_IMAGE = "image"
        const val KEY_USER = "user"
    }



}