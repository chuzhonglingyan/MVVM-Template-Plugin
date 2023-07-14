package com.giffard.mvvm.common.repository

//package com.giffard.mall.article.repository

//import com.giffard.mall.article.model.ArticleTest
//
//interface ArticleRepository {
//
//    suspend fun getList(): List<ArticleTest>
//
//}

fun mvvmRepositoryKt(
    mRootPackageName: String,
    mModuleUpName: String,
    mRepositoryPackageName: String,
    mModelPackageName: String,
    mModelName: String
) = """
package ${mRootPackageName}.${mRepositoryPackageName}

import ${mRootPackageName}.${mModelPackageName}.${mModelName}

interface ${mModuleUpName}Repository {

    suspend fun getList(): List<${mModelName}>

}
"""
