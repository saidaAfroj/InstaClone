package com.example.instragram.fragments

import android.util.Log
import com.example.instragram.MainActivity
import com.example.instragram.Post
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : FeedFragment() {
    override fun queryPosts() {

            // Specify which class to query
            val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
            //Find all post object
            query.include(Post.KEY_USER)
            //Only return posts from currrently signed in user
            query.whereEqualTo(Post.KEY_USER,ParseUser.getCurrentUser())
            //return posts in descending order, newer posts will appear 1st
            query.addDescendingOrder("createdAt")
            //only return 1st 20 posts

            query.findInBackground(object : FindCallback<Post> {
                override fun done(posts: MutableList<Post>?, e: ParseException?) {
                    if(e!= null){
                        //Something has went wrong
                        Log.e(MainActivity.TAG,"Error fetching posts")
                    } else {
                        if( posts != null ){
                            for( post in posts){
                                Log.i(MainActivity.TAG, "Post: " + post.getDescription() + "username :" +post.getUser()?.username)
                            }
                            allPosts.addAll(posts)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }

            })

    }
}