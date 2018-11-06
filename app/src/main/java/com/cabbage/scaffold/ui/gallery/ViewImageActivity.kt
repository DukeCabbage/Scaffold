package com.cabbage.scaffold.ui.gallery

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.cabbage.scaffold.R
import com.cabbage.scaffold.image.GlideApp
import com.cabbage.scaffold.ui.base.BaseActivity

class ViewImageActivity : BaseActivity() {

    @BindView(R.id.iv1)
    lateinit var ivFirst: ImageView

    private var circlePlaceholder: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image)
        ButterKnife.bind(this)

        val intent = intent

        GlideApp.with(this)
                .load(R.drawable.placeholder_square)
                .circleCrop()
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        circlePlaceholder = resource

//                        loadImage()
                        loadGif()
                    }
                })
    }

    private fun loadImage() {
        val post = Post.complete()

        GlideApp.with(this)
//                .load("https://bit.ly/2LXcQvU")
                .load(post.media?.url)
                .thumbnail(0.05f)
//                .thumbnail(GlideApp.with(this)
//                                   .load(post.thumb?.url)
//                                   .circleCrop()
//                                   .listener(glideRequestListener))
//                .fallback(ColorDrawable(Color.CYAN))
//                .error(GlideApp.with(this)
//                               .load("https://bit.ly/2LXcQvU")
//                               .circleCrop())
//                .error(R.drawable.placeholder_square)
                .placeholder(circlePlaceholder)
                .circleCrop()
                .listener(glideRequestListener)
                .into(ivFirst)
    }

    private fun loadGif() {
        val post = Post.kittyAnim()

        GlideApp.with(this)
                .load(post.media?.url)
                .thumbnail(GlideApp.with(this)
                                   .load(post.thumb?.url)
                                   .circleCrop()
                                   .listener(glideRequestListener))
                .placeholder(circlePlaceholder)
                .circleCrop()
                .listener(glideRequestListener)
                .into(ivFirst)
    }

    private val glideRequestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?,
                                  model: Any?,
                                  target: Target<Drawable>?,
                                  isFirstResource: Boolean): Boolean {

            return false
        }

        override fun onResourceReady(resource: Drawable?,
                                     model: Any?,
                                     target: Target<Drawable>?,
                                     dataSource: DataSource?,
                                     isFirstResource: Boolean): Boolean {
            return false
        }
    }
}