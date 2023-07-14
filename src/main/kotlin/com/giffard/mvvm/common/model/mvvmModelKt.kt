package com.giffard.mvvm.common.model

//package com.giffard.mall.article.model
//
//data class ArticleTest(
//    private val id: Int = 0
//)

fun mvvmModelKt(
    mRootPackageName: String,
    mModelPackageName: String,
    mModelName: String,
) = """
package ${mRootPackageName}.${mModelPackageName}

data class ${mModelName}(
    private val id: Int = 0
)
"""
