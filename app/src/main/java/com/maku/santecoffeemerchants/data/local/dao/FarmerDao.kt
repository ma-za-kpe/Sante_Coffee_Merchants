package com.maku.santecoffeemerchants.data.local.dao

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.*
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.data.local.entities.Farmer_ID


@Dao
interface FarmerDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun upsert(farmer: Farmer) //update or insert at the same time

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(farmer: Farmer)

    @Query("SELECT * from farmer_details")
    fun getReminders(): List<Farmer>

    @Query("select * from farmer_details")
    fun getFarmerDetails(): Farmer

    /*
  * Insert the object in database
  * @param note, object to be inserted
  */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(farmer: Farmer?)

    /*
  * update the object in database
  * @param note, object to be updated
  */
    @Update
    fun update(farmer: Farmer?)

    /*
  * delete the object from database
  * @param note, object to be deleted
  */
    @Delete
    fun delete(farmer: Farmer?)

}