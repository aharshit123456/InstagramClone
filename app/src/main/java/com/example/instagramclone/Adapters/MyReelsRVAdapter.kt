package com.example.instagramclone.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.instagramclone.Models.Post
import com.example.instagramclone.Models.Reel
import com.example.instagramclone.databinding.MyPostRvDesignBinding
import com.squareup.picasso.Picasso

class MyReelsRVAdapter(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<MyReelsRVAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: MyPostRvDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MyPostRvDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.postImg
        Glide.with(context)
            .load(reelList.get(position).reelUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.postImg)
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

}