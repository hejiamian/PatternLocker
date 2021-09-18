package com.github.ihsg.demo.ui.whole

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.R
import com.github.ihsg.demo.databinding.ActivityWholePatternSettingBinding
import com.github.ihsg.demo.util.PatternHelper
import com.github.ihsg.patternlocker.DefaultLockerNormalCellView
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView

class WholePatternSettingActivity : BaseActivity<ActivityWholePatternSettingBinding>() {

    private lateinit var patternHelper: PatternHelper

    override fun getViewBinding(): ActivityWholePatternSettingBinding {
        return ActivityWholePatternSettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val decorator = (binding.patternLockerView.normalCellView as DefaultLockerNormalCellView).styleDecorator

        binding.patternLockerView.hitCellView = RippleLockerHitCellView()
                .setHitColor(decorator.hitColor)
                .setErrorColor(decorator.errorColor)

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

        findViewById<View>(R.id.btn_clean).setOnClickListener {
            binding.patternLockerView.clearHitState()
        }
    }

    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        this.patternHelper.validateForSetting(hitIndexList)
        return this.patternHelper.isOk
    }

    private fun updateMsg() {
        binding.textMsg.text = this.patternHelper.message
        binding.textMsg.setTextColor(if (this.patternHelper.isOk)
            ContextCompat.getColor(this, R.color.colorPrimaryDark)
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
            val intent = Intent(context, WholePatternSettingActivity::class.java)
            context.startActivity(intent)
        }
    }
}
