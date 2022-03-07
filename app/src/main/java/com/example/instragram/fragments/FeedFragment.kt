package com.example.instragram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instragram.MainActivity
import com.example.instragram.Post
import com.example.instragram.PostAdapter
import com.example.instragram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery


open class FeedFragment : Fragment() {

    lateinit var postRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter
    var allPosts : MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postRecyclerView = view.findViewById<RecyclerView>(R.id.postRecyclerView)

        //This is where we set up our views and click listners

        //Steps to populate Recyclerview
        //Create layout for each row in list(item_post.xml)
        //create data source for each row (this is the post class)
        //create adapter that will bring data and row layout(postAdapter)
        //set adapter on recyclerview
        adapter = PostAdapter(requireContext(), allPosts)
        postRecyclerView.adapter = adapter
        //set layout manager on recyclerview
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        queryPosts()
    }
    //query for all posts in our server
    open fun queryPosts(){
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //Find all post object
        query.include(Post.KEY_USER)
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

    companion object {
        const val TAG = "FeedFragment"
    }

}