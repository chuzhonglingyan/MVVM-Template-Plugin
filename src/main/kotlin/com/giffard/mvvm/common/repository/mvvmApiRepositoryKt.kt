package com.giffard.mvvm.common.repository

//package com.giffard.mall.article.repository
//
//import com.giffard.mall.article.model.ArticleTest
//
//class ArticleApiRepository : ArticleRepository {
//
//    override suspend fun getList(): List<ArticleTest> {
//        return mockList()
//    }
//
//    private fun mockList(): List<ArticleTest> {
//        val list = mutableListOf<ArticleTest>()
//        for (index in 1 until 11) {
//            list.add(ArticleTest(index))
//        }
//        return list
//    }
//
//}

fun mvvmApiRepositoryKt(
    mRootPackageName: String,
    mModuleName: String,
    mRepositoryPackageName: String,
    mModelPackageName: String,
    mModelName: String
) = """
package ${mRootPackageName}.${mRepositoryPackageName}

import ${mRootPackageName}.${mModelPackageName}.${mModelName}

class ${mModuleName}ApiRepository : ${mModuleName}Repository {

    override suspend fun getList(): List<${mModelName}> {
        return mockList()
    }

    private fun mockList(): List<${mModelName}> {
        val list = mutableListOf<${mModelName}>()
        for (index in 1 until 11) {
            list.add(${mModelName}(index))
        }
        return list
    }

}
 
"""
