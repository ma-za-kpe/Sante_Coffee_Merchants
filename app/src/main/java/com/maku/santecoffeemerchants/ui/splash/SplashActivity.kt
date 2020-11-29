package com.maku.santecoffeemerchants.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maku.santecoffeemerchants.R
import com.maku.santecoffeemerchants.SanteCoffeeFarmers
import com.maku.santecoffeemerchants.ui.MainActivity
import com.maku.santecoffeemerchants.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    val prefManager = SanteCoffeeFarmers.instance!!.prefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (prefManager.isFirstTimeLaunch) {
            gotoLogin()
        } else {
            goToMainActivity()
        }
    }

    // TODO: 11/26/2020 find a better way to dry these 2 sections 
    private fun goToMainActivity() {
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
    }

    private fun gotoLogin() {
        val myIntent = Intent(this, LoginActivity::class.java)
        this.startActivity(myIntent)
    }

}