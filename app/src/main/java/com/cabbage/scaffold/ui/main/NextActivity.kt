package com.cabbage.scaffold.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_next.*
import timber.log.Timber

class NextActivity : BaseActivity() {

    private lateinit var data: String

    private val KEY = "count"
    
    private val sp: SharedPreferences by lazy {
        getSharedPreferences("test", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        data = intent?.getStringExtra("MEOW") ?: "default"

        tvBundleData.text = data


        setupListener()

        btn_increase.setOnClickListener {
            val now = sp.getInt(KEY, 0)
            sp.edit().putInt(KEY, now + 1).apply()
        }

        btn_reset.setOnClickListener {
            sp.edit().remove(KEY).apply()
        }


        btn_sub.setOnClickListener {
            sub = true

            val now = sp.getInt(KEY, 0)
            tvBundleData.text = "$now"
        }

        btn_un_sub.setOnClickListener {
            sub = false
        }
    }

    private var sub = false

    private fun setupListener() {

        sp.registerOnSharedPreferenceChangeListener { sp, key ->
            key.takeIf { it == KEY && sub }?.also {
                val now = sp.getInt(key, 0)
                tvBundleData.text = "$now"
            }
        }
    }
}