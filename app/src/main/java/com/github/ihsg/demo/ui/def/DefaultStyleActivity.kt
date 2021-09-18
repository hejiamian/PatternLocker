package com.github.ihsg.demo.ui.def

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.databinding.ActivityDefaultStyleBinding


class DefaultStyleActivity : BaseActivity<ActivityDefaultStyleBinding>() {

    override fun getViewBinding(): ActivityDefaultStyleBinding {
        return ActivityDefaultStyleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSetting.setOnClickListener {
            DefaultPatternSettingActivity.startAction(this@DefaultStyleActivity)
        }

        binding.btnChecking.setOnClickListener {
            DefaultPatternCheckingActivity.startAction(this@DefaultStyleActivity)
        }
    }

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, DefaultStyleActivity::class.java)
            context.startActivity(intent)
        }
    }
}
