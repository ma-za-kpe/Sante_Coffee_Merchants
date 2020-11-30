package com.maku.santecoffeemerchants.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.maku.santecoffeemerchants.R
import com.maku.santecoffeemerchants.SanteCoffeeFarmers
import com.maku.santecoffeemerchants.data.local.entities.Branch
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.data.local.model.Manager
import com.maku.santecoffeemerchants.databinding.ActivityLoginBinding
import com.maku.santecoffeemerchants.ui.MainActivity
import com.maku.santecoffeemerchants.ui.viewmodel.MainViewModel
import com.maku.santecoffeemerchants.ui.viewmodel.MainViewModelFactory
import com.tapadoo.alerter.Alerter
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class LoginActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()

    private lateinit var binding: ActivityLoginBinding
    private val viewModelFactory: MainViewModelFactory by instance()
    private var viewModel: MainViewModel? = null

    val prefManager = SanteCoffeeFarmers.instance!!.prefManager

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 9001
    private var mAuth: FirebaseAuth? = null

    var firstName: String?=null
    var brandName: String?=null

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize the binding
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )

        binding.login.setOnClickListener {
            googleLogin()
        }

        // [START config_signin]
        // Configure Google Sign In
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        // [END config_signin]
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]

        initViewModels()
        initObservers()
    }

    private fun googleLogin() {
        Timber.d("googleLogin running...")
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        Toast.makeText(this, "Signing in", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            task.addOnCompleteListener { task1 ->
                //handle successfull sign in
                try {
                    Timber.d("isSuccessful running...")
                    val account = task1.result
                    Toast.makeText(this, "Welcome ${account!!.email!!} !", Toast.LENGTH_SHORT).show()

                    //save user to app
                    GoogleManager(account)

                    firebaseAuthWithGoogle(account)

                } catch (e: ApiException) {
                    Timber.d("failed sign-in running...%s", e)
                    Toast.makeText(this, "Welcome ${task1.exception!!.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Timber.d("firebaseAuthWithGoogle running...")
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "signInWithCredential: success", Toast.LENGTH_SHORT).show()

                    val user = task.result?.user
                    //check new account
                    updateUI(user)
                } else {
                    updateUI(null)
                    // If sign in fails, display a message to the user.
                    Alerter.create(this)
                        .setTitle("sign in failed")
                        .setText(task.exception!!.message.toString())
                        .setDuration(10000)
                        .setBackgroundColorRes(R.color.purple_700) // or setBackgroundColorInt(Color.CYAN)
                        .show()

                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user!=null){
            goToMainActivity()
        } else {

        }
    }

    private fun GoogleManager(account: GoogleSignInAccount) {
        val id = account.id.toString()
        firstName = account.displayName.toString()
        val lastName = account.displayName.toString()
        val email = account.email
        val photoUrl = account.photoUrl.toString()
        brandName = binding.branch.text.toString()
        Timber.d("user data $firstName$brandName")

        //send datails to next screen or save in shared preferences
        prefManager.fullName = firstName
        prefManager.branchName = brandName

    }

    private fun goToMainActivity() {
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
    }

    private fun initObservers() {
        val branch = viewModel?.branch
        val manager = Manager(prefManager.fullName.toString())
        val farmers = arrayListOf<Farmer>()

        val b = Branch(farmers,"BRNCH001", manager, prefManager.branchName.toString())
        Timber.d("all branc " + prefManager.branchName)

        //add branch name and its manager to the DB
        viewModel?.addBranch(b)
    }

    private fun initViewModels() {
        if (null == viewModel) {
            //presavation of viewmodels is a job of the viewmodelprovider
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        }
    }

}