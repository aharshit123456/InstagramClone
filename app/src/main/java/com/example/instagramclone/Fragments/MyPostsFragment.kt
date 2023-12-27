package com.example.instagramclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagramclone.Adapters.MyPostRVAdapter
import com.example.instagramclone.Models.Post
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentMyPostsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class MyPostsFragment : Fragment() {
    private lateinit var binding:FragmentMyPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPostsBinding.inflate(inflater, container, false)
        var postList = ArrayList<Post>()
        var adapter = MyPostRVAdapter(requireContext(), postList)
        binding.rv.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            var tempList = arrayListOf<Post>()
            for(i in it.documents){
                var post:Post = i.toObject<Post>()!!
                tempList.add(post)
            }
            postList.addAll(tempList)
        }
        return binding.root
    }

    companion object {

    }
}