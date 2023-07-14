
package com.giffard.mvvm.activity.src.app_package.ui

fun mvvmActivityKt(
    mRootPackageName:String?,
    mActivityPackageName:String,
    mViewModelPackageName: String,
    mPageName:String,
    mActivityLayoutName:String,
    mBindingName:String,
)="""
package ${mRootPackageName}.${mActivityPackageName}

import androidx.activity.viewModels
import ${mRootPackageName}.R
import ${mRootPackageName}.base.BaseMvvMActivity
import ${mRootPackageName}.databinding.${mBindingName}Binding
import ${mRootPackageName}.${mViewModelPackageName}.${mPageName}ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ${mPageName}Activity : BaseMvvMActivity<${mBindingName}Binding>() {

    private val viewModel: ${mPageName}ViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.${mActivityLayoutName}
    }

    override fun initData() {
        super.initData()
    }

    override fun observeData() {
        super.observeData()
    }

    override fun initListener() {
        super.initListener()
    }

}

"""
