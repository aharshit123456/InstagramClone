package com.example.instagramclone.Post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagramclone.Models.Post
import com.example.instagramclone.R
import com.example.instagramclone.Utils.POST
import com.example.instagramclone.Utils.POST_FOLDER
import com.example.instagramclone.Utils.USER_PROFILE_FOLDER
import com.example.instagramclone.Utils.uploadImage
import com.example.instagramclone.databinding.ActivityPostBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    var imageUrl: String? = null

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {
                    binding.selectImg.setImageURI(uri)
                    imageUrl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener { finish() }

        binding.selectImg.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.postButton.setOnClickListener {
            val post: Post = Post(imageUrl!!, binding.caption.editText?.text.toString())

            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post)
                    .addOnSuccessListener {
                        finish()
                    }
            }

        }

    }
}