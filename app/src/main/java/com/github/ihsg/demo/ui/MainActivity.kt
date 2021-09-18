package com.github.ihsg.demo.ui

import android.os.Bundle
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.databinding.ActivityMainBinding
import com.github.ihsg.demo.ui.def.DefaultStyleActivity
import com.github.ihsg.demo.ui.simple.SimpleStyleActivity
import com.github.ihsg.demo.ui.whole.WholeStyleActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnDefault.setOnClickListener {
            DefaultStyleActivity.startAction(this@MainActivity)
        }

        binding.btnSimple.setOnClickListener {
            SimpleStyleActivity.startAction(this@MainActivity)
        }

        binding.btnWhole.setOnClickListener {
            WholeStyleActivity.startAction(this@MainActivity)
        }
    }
}