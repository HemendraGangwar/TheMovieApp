package com.movie.app.common.factoryExtensions

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.movie.app.R
import com.movie.app.data.model.Resource
import com.movie.app.data.model.Status
import org.jetbrains.anko.toast
import kotlin.reflect.KClass

fun checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun Activity.circularRevealedAtCenter(view: View) {
  val cx = (view.left + view.right) / 2
  val cy = (view.top + view.bottom) / 2
  val finalRadius = Math.max(view.width, view.height)

  if (checkIsMaterialVersion() && view.isAttachedToWindow) {
    val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
    view.visibility= View.VISIBLE
    view.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
    anim.duration = 550
    anim.start()
  }
}

fun Activity.requestGlideListener(view: View): RequestListener<Drawable> {
  return object : RequestListener<Drawable> {
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
      return false
    }

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
      circularRevealedAtCenter(view)
      return false
    }
  }
}

fun AppCompatActivity.setSupportHeader(toolbar: Toolbar, title_: String = "") {
  setSupportActionBar(toolbar)
  supportActionBar?.run {
    setDisplayHomeAsUpEnabled(true)
    setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
  }
}

fun AppCompatActivity.customizeHeader(toolbar: Toolbar) {
  /*if (checkIsMaterialVersion()) {
    toolbar.layoutParams = (toolbar.layoutParams as CollapsingToolbarLayout.LayoutParams).apply {
      topMargin = getStatusBarSize()
    }
  }*/
}

fun <T : ViewModel> AppCompatActivity.vm(factory: ViewModelProvider.Factory, model: KClass<T>): T {
  return ViewModelProviders.of(this, factory).get(model.java)
}
private fun AppCompatActivity.getStatusBarSize(): Int {
  val idStatusBarHeight = resources.getIdentifier("status_bar_height", "dimen", "android")
  return if (idStatusBarHeight > 0) {
    resources.getDimensionPixelSize(idStatusBarHeight)
  } else {
    0
  }
}


fun View.bindResource(resource: Resource<Any>?, onSuccess: () -> Unit) {
  if (resource != null) {
    when (resource.status) {
      Status.LOADING -> Unit
      Status.SUCCESS -> onSuccess()
      Status.ERROR -> this.context.toast(resource.errorEnvelope?.status_message.toString())
    }
  }
}


fun View.visible() {
  visibility = View.VISIBLE
}

fun View.inVisible() {
  visibility = View.INVISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun View.requestGlideListener(): RequestListener<Drawable> {
  return object : RequestListener<Drawable> {
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
      return false
    }

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
      circularRevealedAtCenter()
      return false
    }
  }
}


fun View.circularRevealedAtCenter() {
  val view = this
  val cx = (view.left + view.right) / 2
  val cy = (view.top + view.bottom) / 2
  val finalRadius = Math.max(view.width, view.height)

  if (checkIsMaterialVersion() && view.isAttachedToWindow) {
    val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
    view.visible()
    view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.background))
    anim.duration = 550
    anim.start()
  }
}

