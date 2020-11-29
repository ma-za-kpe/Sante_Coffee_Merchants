package com.maku.santecoffeemerchants.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maku.santecoffeemerchants.data.local.convertors.*
import com.maku.santecoffeemerchants.data.local.dao.BranchDao
import com.maku.santecoffeemerchants.data.local.dao.BranchResponseDao
import com.maku.santecoffeemerchants.data.local.dao.FarmerDao
import com.maku.santecoffeemerchants.data.local.entities.Branch
import com.maku.santecoffeemerchants.data.local.entities.BranchDetailsResponse
import com.maku.santecoffeemerchants.data.local.entities.Farmer

@Database(
        entities = [Farmer::class, Branch::class, BranchDetailsResponse::class],
        version = 6,
        exportSchema = false
    )
    @TypeConverters(ArraylistListConvertor::class, ListConvertor::class, BranchConvertor::class, BirthCertificateConvertor::class, NationaIDConvertor::class, ManagerConvertor::class, FarmerConvertor::class)
    abstract class BranchDB: RoomDatabase() {
        abstract fun farmerDao(): FarmerDao
        abstract fun branchDao(): BranchDao
        abstract fun responseDao(): BranchResponseDao

        companion object {
            @Volatile private var instance: BranchDB? = null //all threads will have immediate access to this property - volatile
            private val LOCK = Any() //dummy object to make sure no two threads are doing the same thing

            operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    BranchDB::class.java, "Branch.db")
                    .fallbackToDestructiveMigration()
                    .build()
        }
}