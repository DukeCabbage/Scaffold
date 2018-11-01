package com.cabbage.scaffold.ui.main

import android.os.Bundle
import android.support.design.chip.ChipGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class NextActivity : BaseActivity() {

    @BindView(R.id.tvBundleData)
    lateinit var tvBundleData: TextView

    @BindView(R.id.chip_group)
    lateinit var chipGroup: ChipGroup


    @Inject lateinit var rxPermission: RxPermissions

    lateinit var data: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        Timber.d("onCreate")
        setContentView(R.layout.activity_next)
        ButterKnife.bind(this)

        data = intent?.getStringExtra("MEOW") ?: "default"

        tvBundleData.text = data

        chipGroup.setOnCheckedChangeListener { chipGroup, i ->
            Timber.d("On check $i")
        }
    }
}