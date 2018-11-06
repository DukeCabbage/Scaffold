package com.cabbage.scaffold.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.shouldUseAltTheme

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (shouldUseAltTheme()) {
            setTheme(R.style.AltAppTheme_NoActionBar)
        } else {
            setTheme(R.style.AppTheme_NoActionBar)
        }
    }
}