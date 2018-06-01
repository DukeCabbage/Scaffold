package com.cabbage.scaffold.ui.demo

import android.os.Bundle
import butterknife.ButterKnife
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_next.*
import timber.log.Timber

class NextActivity : BaseActivity() {

    lateinit var data: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate")
        setContentView(R.layout.activity_next)
        ButterKnife.bind(this)

        data = intent?.getStringExtra("MEOW") ?: "default"

        tvBundleData.text = data
    }
}