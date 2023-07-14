package com.giffard.mvvm.common.di

//package com.giffard.mall.article.di
//
//import com.giffard.mall.article.repository.ArticleApiRepository
//import com.giffard.mall.article.repository.ArticleRepository
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object ArticleRepositoryModule {
//
//    @Provides
//    @Singleton
//    fun provideArticleApiRepository(): ArticleApiRepository {
//        return ArticleApiRepository()
//    }
//
//    @Provides
//    @Singleton
//    fun provideArticleRepository(articleApiRepository: ArticleApiRepository): ArticleRepository {
//        return articleApiRepository
//    }
//}

fun mvvmDIKt(
    mRootPackageName: String,
    mModuleName: String,
    mModelUpName: String,
    mDIPackageName: String,
    mRepositoryPackageName: String,
) = """
package ${mRootPackageName}.${mDIPackageName}

import ${mRootPackageName}.${mRepositoryPackageName}.${mModelUpName}ApiRepository
import ${mRootPackageName}.${mRepositoryPackageName}.${mModelUpName}Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ${mModelUpName}RepositoryModule {

    @Provides
    @Singleton
    fun provide${mModelUpName}ApiRepository(): ${mModelUpName}ApiRepository {
        return ${mModelUpName}ApiRepository()
    }

    @Provides
    @Singleton
    fun provide${mModelUpName}Repository(${mModuleName}ApiRepository: ${mModelUpName}ApiRepository): ${mModelUpName}Repository {
        return ${mModuleName}ApiRepository
    }
}
"""
