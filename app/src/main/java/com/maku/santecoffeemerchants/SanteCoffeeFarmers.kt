package com.maku.santecoffeemerchants

import android.app.Application
import com.maku.santecoffeemerchants.BuildConfig.DEBUG
import com.maku.santecoffeemerchants.data.local.BranchDB
import com.maku.santecoffeemerchants.data.repo.BranchRepo
import com.maku.santecoffeemerchants.data.repo.BranchRepoImpl
import com.maku.santecoffeemerchants.ui.viewmodel.MainViewModelFactory
import com.maku.santecoffeemerchants.utils.PrefManager
import com.maku.santecoffeemerchants.utils.ReleaseLogTree
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class SanteCoffeeFarmers: Application(), KodeinAware {
    companion object {
        // Singleton instance
        // Getter to access Singleton instance
        var instance: SanteCoffeeFarmers? = null
            private set
    }

    val prefManager: PrefManager
        get() {
            return PrefManager.getInstance(this)!!
        }

    override fun onCreate() {
        super.onCreate()

        // Setup singleton instance
        instance = this

        //plant timber
        if (DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                //Add the line number to the tag
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ": " + element.lineNumber
                }
            })
        } else {
            //Release mode
            Timber.plant(ReleaseLogTree())
        }

    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@SanteCoffeeFarmers))

        bind() from singleton { BranchDB(instance()) }
        bind() from singleton { instance<BranchDB>().farmerDao()}
        bind() from singleton { instance<BranchDB>().branchDao()}
        bind() from singleton { instance<BranchDB>().responseDao()}

        bind<BranchRepo>() with singleton { BranchRepoImpl(instance(), instance(), instance( )) }

        bind() from provider { MainViewModelFactory(instance()) }
    }
}