package com.github.ihsg.demo.ui.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.R
import com.github.ihsg.demo.databinding.ActivitySimplePatternSettingBinding
import com.github.ihsg.demo.util.PatternHelper
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView

class SimplePatternSettingActivity : BaseActivity<ActivitySimplePatternSettingBinding>() {

    private lateinit var patternHelper: PatternHelper

    override fun getViewBinding(): ActivitySimplePatternSettingBinding {
        return ActivitySimplePatternSettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.patternLockerView.setOnPatternChangedListener(object : OnPatternChangeListener {
            override fun onStart(view: PatternLockerView) {}

            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {}

            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                val isOk = isPatternOk(hitIndexList)
                view.updateStatus(!isOk)
                binding.patternIndicatorView.updateState(hitIndexList, !isOk)
                updateMsg()
            }

            override fun onClear(view: PatternLockerView) {
                finishIfNeeded()
            }
        })

        binding.textMsg.text = "设置解锁图案"
        this.patternHelper = PatternHelper()
    }

    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        this.patternHelper.validateForSetting(hitIndexList)
        return this.patternHelper.isOk
    }

    private fun updateMsg() {
        binding.textMsg.text = this.patternHelper.message
        binding.textMsg.setTextColor(if (this.patternHelper.isOk)
            ContextCompat.getColor(this, R.color.colorAccent)
        else
            ContextCompat.getColor(this, R.color.color_red))
    }

    private fun finishIfNeeded() {
        if (this.patternHelper.isFinish) {
            finish()
        }
    }

    companion object {


        fun startAction(context: Context) {
            val intent = Intent(context, SimplePatternSettingActivity::class.java)
            context.startActivity(intent)
        }
    }
}
