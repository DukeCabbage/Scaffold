package com.cabbage.scaffold.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import android.graphics.Point
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.widget.Toast

@Suppress("unused")
object ViewUtil {
    fun getStatusBarHeight(context: Context): Int? {
        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resId > 0) context.resources.getDimensionPixelOffset(resId)
        else null
    }

    fun convertDpToPixel(context: Context, dp: Int): Float {
        val metrics = context.resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPixelsToDp(context: Context, px: Float): Int {
        val metrics = context.resources.displayMetrics
        return (px * DisplayMetrics.DENSITY_DEFAULT / metrics.densityDpi).toInt()
    }
}

val Activity.isLandscape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val Fragment.isLandscape: Boolean?
    get() = activity?.isLandscape

fun Context.toast(messageResId: Int, duration: Int = Toast.LENGTH_LONG) =
        Toast.makeText(this, messageResId, duration).show()

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) =
        Toast.makeText(this, message, duration).show()

// Reminder: https://kotlinlang.org/docs/reference/extensions.html#extensions-are-resolved-statically
val Activity?.windowWidth: Int
    get() {
        val size = Point()
        this?.windowManager?.defaultDisplay?.getSize(size)
        return size.x
    }

val Activity?.windowHeight: Int
    get() {
        val size = Point()
        this?.windowManager?.defaultDisplay?.getSize(size)
        return size.y
    }

fun Activity.showAlertDialog(onPositive: () -> Unit = {},
                             onNegative: (() -> Unit)? = null,
                             cancelable: Boolean = true): AlertDialog {
    val builder = AlertDialog.Builder(this)
            .setTitle("Haha")
            .setMessage("Meow meow~")
            .setCancelable(cancelable)
            .setPositiveButton(android.R.string.yes) { _, _ -> onPositive() }

    if (onNegative != null) builder.setNegativeButton(android.R.string.no) { _, _ -> onNegative() }

    return builder.show()
}

//region Dynamic themes
fun Context.shouldUseAltTheme(): Boolean =
        this.getSharedPreferences("default", MODE_PRIVATE)
                .getBoolean("use_alt_theme", false)

fun Context.toggleAltTheme(use: Boolean) = apply {
    this.getSharedPreferences("default", MODE_PRIVATE).edit()
            .putBoolean("use_alt_theme", use)
            .apply()
}
//endregion