package com.maku.santecoffeemerchants.ui.farmersList

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maku.santecoffeemerchants.R
import com.maku.santecoffeemerchants.SanteCoffeeFarmers
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.data.local.model.BirthCertificate
import com.maku.santecoffeemerchants.data.local.model.NationalIdNum
import com.maku.santecoffeemerchants.databinding.FragmentFarmersListBinding
import com.maku.santecoffeemerchants.ui.viewmodel.MainViewModel
import com.maku.santecoffeemerchants.ui.viewmodel.MainViewModelFactory
import com.maku.santecoffeemerchants.utils.ScopedFragment
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class FarmersListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    val prefManager = SanteCoffeeFarmers.instance!!.prefManager

    private lateinit var binding: FragmentFarmersListBinding
    private val viewModelFactory: MainViewModelFactory by instance()
    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_farmers_list, container, false
        )
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("name %s", prefManager.fullName)

        initUiBindings()
        initObservers()
        initViewModels()
    }

    private fun initObservers() = launch  {
        val farmer = viewModel?.farmers?.await()
        val x = viewModel?.farmer?.await()

        farmer?.observe(viewLifecycleOwner, { f ->
            Timber.d("farmer x " + f)
        })

        farmer?.observe(viewLifecycleOwner, { f ->
            // initially, the room db is empty, so handle that state.
            if(f == null){
                Timber.d("data is null AF")
                binding.empty.visibility = View.VISIBLE
                binding.empty.text = "Empty List , Please Add data..."
            } else {
                val list = arrayListOf(f)
                Timber.d("all farmers %s", list.size)
                renderList(list)
            }
        })

    }

    private fun renderList(data: ArrayList<Farmer>) {

        Timber.d("farmer not nulllllllllll %s", data)

                    if ( binding.farmerList.adapter?.itemCount == 0) {
                        setRecyclerData(data )
                    } else { //when load more
                        if (binding.farmerList.adapter == null) { //after load more
                            setRecyclerData(data)
                        }
                        binding.farmerList.adapter?.notifyDataSetChanged()
                    }

    }

    private fun setRecyclerData(photos: ArrayList<Farmer>) {
        with(binding.farmerList) {
            adapter = FarmersAdapter(photos)
        }
    }

    private fun initViewModels() {
        if (null == viewModel) {
            //presavation of viewmodels is a job of the viewmodelprovider
            viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
        }
    }

    private fun initUiBindings() {

        //floating add button
        binding.floatingAddButton.setOnClickListener { view ->
//            Snackbar.make(view, "Here's a Snackbar",
//                Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()
            showAddFarmerDialog(view)
        }
        //recyclerview
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.farmerList.run {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun showAddFarmerDialog(view: View?) {

        // Create an alert builder
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Name")

        // set the custom layout
        val customLayout: View = layoutInflater
            .inflate(
                R.layout.enter_farmer_view,
                null
            )
        builder.setView(customLayout)

        // add a button
        builder.setPositiveButton(
            "OK"
        ) { dialog, which -> // send data from the
                // AlertDialog to the Activity
            // TODO: 11/28/2020 : find a better way of doing this ... data-binding
            val name = customLayout.findViewById<EditText>(R.id.name)
            val dob = customLayout.findViewById<EditText>(R.id.dob)
            val cardno = customLayout.findViewById<EditText>(R.id.cardno)
            val date = customLayout.findViewById<EditText>(R.id.date)
            val dobb = customLayout.findViewById<EditText>(R.id.dobb)
            val givenname = customLayout.findViewById<EditText>(R.id.givenname)
            val ID = customLayout.findViewById<EditText>(R.id.ID)
            val nationality = customLayout.findViewById<EditText>(R.id.nationality)
            val sex = customLayout.findViewById<EditText>(R.id.sex)
            val surname = customLayout.findViewById<EditText>(R.id.surname)
            val phone = customLayout.findViewById<EditText>(R.id.phone)

            sendDialogDataToDB(
                name.text.toString(),
                dob.text.toString(),
                cardno.text.toString(),
                date.text.toString(),
                dobb.text.toString(),
                givenname.text.toString(),
                ID.text.toString(),
                nationality.text.toString(),
                sex.text.toString(),
                surname.text.toString(),
                phone.text.toString()
            )
            }

        // create and show
        // the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun sendDialogDataToDB(
        name: String,
        dob: String,
        cardno: String,
        date: String,
        dobb: String,
        givenname: String,
        id: String,
        nationality: String,
        sex: String,
        surname: String,
        phone: String
    ) = launch {
        val birthCertificate = BirthCertificate(dob, name)
        val nationalIdNum = NationalIdNum(
            cardno,
            date,
            dobb,
            givenname,
            id,
            nationality,
            sex,
            surname
        )

        val farmer = Farmer(birthCertificate, nationalIdNum, phone)

        viewModel?.addFarmer(farmer)
        binding.empty.visibility = View.GONE

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FarmersListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}