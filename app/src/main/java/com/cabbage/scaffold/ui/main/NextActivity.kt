package com.cabbage.scaffold.ui.main

import android.os.Bundle
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_next.*
import timber.log.Timber
import javax.inject.Inject

class NextActivity : BaseActivity() {

    @Inject lateinit var rxPermission: RxPermissions

    lateinit var data: String

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        data = intent?.getStringExtra("MEOW") ?: "default"

        tvBundleData.text = data

        chip_group.setOnCheckedChangeListener { _, i ->
            Timber.d("On check $i")
        }
    }
}