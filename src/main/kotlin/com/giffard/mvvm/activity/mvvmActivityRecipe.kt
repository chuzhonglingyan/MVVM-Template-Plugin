package com.giffard.mvvm.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.giffard.mvvm.activity.src.app_package.ui.mvvmActivityKt
import com.giffard.mvvm.activity.src.app_package.ui.mvvmFragmentKt
import com.giffard.mvvm.activity.src.app_package.ui.mvvmLazyFragmentKt
import com.giffard.mvvm.common.di.mvvmDIKt
import com.giffard.mvvm.common.model.mvvmModelKt
import com.giffard.mvvm.common.repository.mvvmApiRepositoryKt
import com.giffard.mvvm.common.repository.mvvmRepositoryKt
import com.giffard.mvvm.common.res.layout.mvvmXml
import com.giffard.mvvm.common.viewmodel.mvvmViewModelKt
import java.io.File
import java.util.Locale

/**
 *  mRootPackageName : 包名
 *  mPageName : 模块名
 *  mActivityPackageName : Activity包名
 */
fun RecipeExecutor.mvvmActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mRootPackageName: String,
    mModuleName: String,
    mPageName: String,
    mIsActivity: Boolean,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mActivityPackageName: String,
    mIsUseHilt: Boolean,
    mIsFragment: Boolean,
    mIsLazyFragment: Boolean,
    mFragmentLayoutName: String,
    mIsGenerateFragmentLayout: Boolean,
    mFragmentPackageName: String
) {
    val (projectData, srcOut, resOut) = moduleTemplateData
    val ktOrJavaExt = projectData.language.extension

    val mModuleUpName = mModuleName.replaceFirstChar { it.uppercaseChar() }

    val viewModelPackageName = "${mModuleName}.viewmodel".lowercase(Locale.getDefault())
    val activityPackageName = "${mModuleName}.ui".lowercase(Locale.getDefault())
    val mRepositoryPackageName = "${mModuleName}.repository".lowercase(Locale.getDefault())
    val mDIPackageName = "${mModuleName}.di".lowercase(Locale.getDefault())

    val mModelPackageName = "${mModuleName}.model".lowercase(Locale.getDefault())
    val mModelName = mPageName

    if (mIsActivity) {
        val activityName = "${mPageName}Activity"
        //清单文件配置  .article.ui.ArticleTestActivity
        generateManifest(
            moduleData = moduleTemplateData,
            activityClass = activityName,
            packageName = ".${activityPackageName}",
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
        )

        //layout xml 文件名生成binding名
        val transActivityBinding = StringBuilder()
        mActivityLayoutName.split("_").onEach { str ->
            val s = str.replaceFirstChar { it.uppercaseChar() }
            transActivityBinding.append(s)
        }

        val mvvmActivity = mvvmActivityKt(
            mRootPackageName,
            activityPackageName,
            viewModelPackageName,
            mPageName,
            mActivityLayoutName,
            transActivityBinding.toString()
        )
        // 保存Activity
        save(
            mvvmActivity,
            srcOut.resolve("${activityPackageName.toPath()}/${mPageName}Activity.${ktOrJavaExt}")
        )
        if (mIsGenerateActivityLayout) {
            // 保存xml
            save(
                mvvmXml(
                    mRootPackageName,
                    viewModelPackageName,
                    mPageName
                ),
                resOut.resolve("layout/${mActivityLayoutName}.xml")
            )
        }

    } else if (mIsFragment) {

        //layout xml 文件名生成binding名
        val transFragmentBinding = StringBuilder()
        mFragmentLayoutName.split("_").onEach {
            val s = it.capitalize()
            transFragmentBinding.append(s)
        }

        val mvvmFragment: String = if (mIsLazyFragment) {

            mvvmLazyFragmentKt(
                mRootPackageName,
                mFragmentPackageName.replace("/", "."),
                mPageName,
                transFragmentBinding.toString()
            )
        } else {
            mvvmFragmentKt(
                mRootPackageName,
                mFragmentPackageName.replace("/", "."),
                mPageName,
                transFragmentBinding.toString()
            )
        }

        // 保存Fragment
        save(
            mvvmFragment,
            srcOut.resolve("${mFragmentPackageName}/${mPageName}Fragment.${ktOrJavaExt}")
        )
        if (mIsGenerateFragmentLayout) {
            // 保存xml
            save(
                mvvmXml(mRootPackageName, viewModelPackageName, mPageName),
                resOut.resolve("layout/${mFragmentLayoutName}.xml")
            )
        }
    }

    // model: com.giffard.mall.article.model
    val modelPath =
        "${mModelPackageName.toPath()}/${mModelName}.${ktOrJavaExt}"
    save(
        mvvmModelKt(
            mRootPackageName,
            mModelPackageName,
            mModelName,
        ),
        srcOut.resolve(modelPath)
    )

    // 保存viewmodel: com.giffard.mall.article.viewmodel
    val viewmodelPath =
        "${viewModelPackageName.toPath()}/${mPageName}ViewModel.${ktOrJavaExt}"
    save(
        mvvmViewModelKt(
            mRootPackageName,
            mModuleName,
            mModuleUpName,
            viewModelPackageName,
            mRepositoryPackageName,
            mModelPackageName,
            mPageName,
        ),
        srcOut.resolve(viewmodelPath)
    )
    // 保存repository
    val repositoryPath =
        "${mRepositoryPackageName.toPath()}/${mModuleUpName}Repository.${ktOrJavaExt}"
    val repositoryFile = File(repositoryPath)
    if (!repositoryFile.exists()) {
        save(
            mvvmRepositoryKt(
                mRootPackageName,
                mModuleUpName,
                mRepositoryPackageName,
                mModelPackageName,
                mModelName,
            ),
            srcOut.resolve(repositoryPath)
        )
    }

    // 保存apiRepository
    val apiRepositoryPath =
        "${mRepositoryPackageName.toPath()}/${mModuleUpName}ApiRepository.${ktOrJavaExt}"
    val apiRepositoryFile = File(apiRepositoryPath)
    if (!apiRepositoryFile.exists()) {
        save(
            mvvmApiRepositoryKt(
                mRootPackageName,
                mModuleUpName,
                mRepositoryPackageName,
                mModelPackageName,
                mModelName,
            ),
            srcOut.resolve(apiRepositoryPath)
        )
    }

    // 保存di
    val diRepositoryPath =
        "${mDIPackageName.toPath()}/${mModuleUpName}RepositoryModule.${ktOrJavaExt}"
    save(
        mvvmDIKt(
            mRootPackageName,
            mModuleName,
            mModuleUpName,
            mDIPackageName,
            mRepositoryPackageName,
        ),
        srcOut.resolve(diRepositoryPath)
    )
}


fun String.toPath(): String {
    return this.replace(".", "/")
}

fun String.toPackageName(): String {
    return this.replace("/", ".")
}

