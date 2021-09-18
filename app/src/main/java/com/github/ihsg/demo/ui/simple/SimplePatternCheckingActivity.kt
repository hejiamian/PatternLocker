package com.github.ihsg.demo.ui.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.github.ihsg.base.BaseActivity
import com.github.ihsg.demo.R
import com.github.ihsg.demo.databinding.ActivitySimplePatternCheckingBinding
import com.github.ihsg.demo.util.PatternHelper
import com.github.ihsg.patternlocker.DefaultIndicatorNormalCellView
import com.github.ihsg.patternlocker.DefaultLockerNormalCellView
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView

class SimplePatternCheckingActivity : BaseActivity<ActivitySimplePatternCheckingBinding>() {

    private lateinit var patternHelper: PatternHelper

    override fun getViewBinding(): ActivitySimplePatternCheckingBinding {
        return ActivitySimplePatternCheckingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pivStyle = (binding.patternIndicatorView.normalCellView as DefaultIndicatorNormalCellView).styleDecorator
        pivStyle.normalColor = ContextCompat.getColor(this, R.color.colorWhite)
        pivStyle.fillColor = ContextCompat.getColor(this, R.color.color_blue)
        pivStyle.hitColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        pivStyle.errorColor = ContextCompat.getColor(this, R.color.color_red)
        pivStyle.lineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f,
            resources.displayMetrics)

        val plvStyle = (binding.patternLockerView.normalCellView as DefaultLockerNormalCellView).styleDecorator
        plvStyle.normalColor = ContextCompat.getColor(this, R.color.colorWhite)
        plvStyle.fillColor = ContextCompat.getColor(this, R.color.color_blue)
        plvStyle.hitColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        plvStyle.errorColor = ContextCompat.getColor(this, R.color.color_red)
        plvStyle.lineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f,
            resources.displayMetrics)

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
        this.patternHelper.validateForChecking(hitIndexList)
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
            val intent = Intent(context, SimplePatternCheckingActivity::class.java)
            context.startActivity(intent)
        }
    }
}
