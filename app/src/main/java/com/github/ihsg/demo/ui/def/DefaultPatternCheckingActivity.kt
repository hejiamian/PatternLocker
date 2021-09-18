package com.github.ihsg.demo.ui.def

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.R
import com.github.ihsg.demo.databinding.ActivityDefaultPatternCheckingBinding
import com.github.ihsg.demo.util.PatternHelper
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView


class DefaultPatternCheckingActivity : BaseActivity<ActivityDefaultPatternCheckingBinding>() {
    private var patternHelper: PatternHelper? = null

    override fun getViewBinding(): ActivityDefaultPatternCheckingBinding {
        return ActivityDefaultPatternCheckingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.patternLockerView.linkedLineView = null
        binding.patternLockerView.hitCellView = null
        binding.patternLockerView.setOnPatternChangedListener(object : OnPatternChangeListener {
            override fun onStart(view: PatternLockerView) {}

            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {}

            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                val isError = !isPatternOk(hitIndexList)
                view.updateStatus(isError)
                binding.patternIndicatorView.updateState(hitIndexList, isError)
                updateMsg()
            }

            override fun onClear(view: PatternLockerView) {
                finishIfNeeded()
            }
        })

        binding.textMsg.text = "绘制解锁图案"
        this.patternHelper = PatternHelper()
    }

    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        this.patternHelper!!.validateForChecking(hitIndexList)
        return this.patternHelper!!.isOk
    }

    private fun updateMsg() {
        binding.textMsg.text = this.patternHelper!!.message
        binding.textMsg.setTextColor(if (this.patternHelper!!.isOk)
            ContextCompat.getColor(this, R.color.colorPrimary)
        else
            ContextCompat.getColor(this, R.color.colorAccent))
    }

    private fun finishIfNeeded() {
        if (this.patternHelper!!.isFinish) {
            finish()
        }
    }

    companion object {

        fun startAction(context: Context) {
            val intent = Intent(context, DefaultPatternCheckingActivity::class.java)
            context.startActivity(intent)
        }
    }
}
