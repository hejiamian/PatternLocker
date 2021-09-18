package com.github.ihsg.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<out T : ViewBinding> : AppCompatActivity() {
    private val _binding: T by lazy {
        getViewBinding()
    }

    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
    }

    abstract fun getViewBinding(): T
}