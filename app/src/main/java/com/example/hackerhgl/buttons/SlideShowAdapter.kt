package com.example.hackerhgl.buttons

import android.content.Context
import android.content.res.Resources
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.hackerhgl.buttons.R.string.images
import android.graphics.BitmapFactory
import android.graphics.Bitmap





/**
 * Created by hackerhgl on 12/15/17.
 */

class SlideShowAdapter (context: Context) : PagerAdapter() {
    private val context = context
    private lateinit var inflater: LayoutInflater

    private var images= intArrayOf(
            R.drawable.itachi,
            R.drawable.deadpool,
            R.drawable.madara,
            R.drawable.material,
            R.drawable.sasuke,
            R.drawable.zoro
    )

    private fun getCacheImage(index: Int, init: Boolean = false): Bitmap {
        return decodeImage(context.resources, images[index], 300, 300)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj as LinearLayout
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.slideshow, container, false)
        val img = view.findViewById<ImageView>(R.id.imageView_id)
        img.setImageResource(images[position])
//        val image: Bitmap = getCacheImage(position)
//        img.setImageBitmap(image)
        container.addView(view)
        return view
    }

    fun calculateInSampleSize(
            options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }


    fun decodeImage(res: Resources, resId: Int,
                                        reqWidth: Int, reqHeight: Int): Bitmap {

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as LinearLayout)
    }
}