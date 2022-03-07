package com.example.instragram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.instragram.fragments.ComposeFragment
import com.example.instragram.fragments.FeedFragment
import com.example.instragram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

/**
 *
 * Let the user create a post by taking a photo with their camera
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager

        // We move the whole part to fragment compose xml file.
        //1.Setting the description of the post
        //2.A button to launch the camera to take a picture
        //3.An imageview to show the picture to the user has taken
        //4.A button to save and send to our parse server
        //queryPosts()


        //Following function will get id of bottom navigation bar and perform itemselectedListner

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {



            //Return true to say that we've handled this user interaction on the item .

            item->
            var fragmentToShow : Fragment? = null

            when(item.itemId)
            {
                R.id.action_home ->{
                    //Navigate to home fragment
                    fragmentToShow = FeedFragment()

                }
                R.id.action_compose -> {
                    //Navigate to compose fragment
                    fragmentToShow = ComposeFragment()

                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                    // navigate to user profile
                    //Toast.makeText(this, "Profile" , Toast.LENGTH_SHORT).show();
                }

            }
            if(fragmentToShow != null)
            {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            true;
        }
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home


    }
    companion object {
        const val TAG = "MainActivity"
    }

}