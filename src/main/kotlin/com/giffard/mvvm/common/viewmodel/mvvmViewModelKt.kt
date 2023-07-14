package com.giffard.mvvm.common.viewmodel

//package com.giffard.mall.article.viewmodel
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.giffard.mall.article.model.ArticleTest
//import com.giffard.mall.article.repository.ArticleRepository
//import com.giffard.mall.base.BaseViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class ArticleTestViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
//    BaseViewModel() {
//
//    val list = MutableLiveData<List<ArticleTest>>()
//
//    init {
//        getList()
//    }
//
//    private fun getList() {
//        viewModelScope.launch {
//            executeInBackground {
//                articleRepository.getList()
//            }.onSuccess {
//                list.value = it
//            }
//        }
//    }
//
//}

fun mvvmViewModelKt(
    mRootPackageName: String,
    mModuleName: String,
    mModuleUpName: String,
    mViewModelPackageName: String,
    mRepositoryPackageName: String,
    mModelPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}.${mViewModelPackageName}

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ${mRootPackageName}.${mModelPackageName}.${mPageName}
import ${mRootPackageName}.${mRepositoryPackageName}.${mModuleUpName}Repository
import ${mRootPackageName}.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ${mPageName}ViewModel @Inject constructor(private val ${mModuleName}Repository: ${mModuleUpName}Repository) :
    BaseViewModel() {

    val list = MutableLiveData<List<${mPageName}>>()

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            executeInBackground {
                ${mModuleName}Repository.getList()
            }.onSuccess {
                list.value = it
            }
        }
    }

}
"""