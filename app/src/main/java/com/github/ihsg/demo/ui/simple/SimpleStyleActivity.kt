package com.github.ihsg.demo.ui.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.databinding.ActivitySimpleStyleBinding

class SimpleStyleActivity : BaseActivity<ActivitySimpleStyleBinding>() {

    override fun getViewBinding(): ActivitySimpleStyleBinding {
        return ActivitySimpleStyleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSetting.setOnClickListener {
            SimplePatternSettingActivity.startAction(this@SimpleStyleActivity)
        }

        binding.btnChecking.setOnClickListener {
            SimplePatternCheckingActivity.startAction(this@SimpleStyleActivity)
        }
    }

    companion object {

        fun startAction(context: Context) {
            val intent = Intent(context, SimpleStyleActivity::class.java)
            context.startActivity(intent)
        }
    }
}
