package com.github.ihsg.demo.ui.whole

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.databinding.ActivityWholeStyleBinding

class WholeStyleActivity : BaseActivity<ActivityWholeStyleBinding>() {
    override fun getViewBinding(): ActivityWholeStyleBinding {
        return ActivityWholeStyleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSetting.setOnClickListener {
            WholePatternSettingActivity.startAction(this@WholeStyleActivity)
        }
        binding.btnChecking.setOnClickListener {
            WholePatternCheckingActivity.startAction(this@WholeStyleActivity)
        }
    }

    companion object {

        fun startAction(context: Context) {
            val intent = Intent(context, WholeStyleActivity::class.java)
            context.startActivity(intent)
        }
    }
}
