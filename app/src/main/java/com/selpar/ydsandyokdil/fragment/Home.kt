package com.selpar.ydsandyokdil.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.selpar.ydsandyokdil.R
import java.util.Timer
import java.util.TimerTask

class ImageSliderAdapter(private val images: Array<Int>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_item, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        val image = images[position]
        holder.imageView.setImageResource(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}

class Home : Fragment() {

    private val images = arrayOf(
        R.drawable.tn_r1,
        R.drawable.tn_r2,
        R.drawable.tn_r3,
        R.drawable.tn_r4,
        // Add more images here as needed
    )
    private lateinit var viewPager: ViewPager2
    private val timer = Timer()
    private var currentPage = 0
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        imageSliderAdapter = ImageSliderAdapter(images)
        viewPager.adapter = imageSliderAdapter
        startImageSlideshow()
        return view
    }
    private fun startImageSlideshow() {
        val handler = android.os.Handler()

        val update = Runnable {
            if (currentPage == images.size) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }

        // Delay in milliseconds between image transitions
        val delay = 3000L

        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, delay, delay)
    }

}