package com.maku.santecoffeemerchants.ui.farmersList

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maku.santecoffeemerchants.R
import com.maku.santecoffeemerchants.SanteCoffeeFarmers
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.data.local.model.BirthCertificate
import com.maku.santecoffeemerchants.data.local.model.NationalIdNum
import com.maku.santecoffeemerchants.databinding.FragmentFarmersListBinding
import com.maku.santecoffeemerchants.ui.viewmodel.MainViewModel
import com.maku.santecoffeemerchants.ui.viewmodel.MainViewModelFactory
import com.maku.santecoffeemerchants.utils.DialogUtils
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

        Timber.d("farmer a " + farmer)
        Timber.d("farmer b " + x)

        renderList(x as ArrayList<Farmer>)

//        x?.observe(viewLifecycleOwner) { f ->
//            // initially, the room db is empty, so handle that state.
//            if (f == null) {
//                Timber.d("data is null AF")
//                binding.empty.visibility = View.VISIBLE
//                binding.empty.text = "Empty List , Please Add data..."
//            } else {
//                val list = arrayListOf(f)
//                Timber.d("all farmers %s", list.size)
//                renderList(list)
//            }
//        }

    }

    private fun renderList(data: ArrayList<Farmer>) {

        Timber.d("farmer not nulllllllllll %s", data)

                    if ( binding.farmerList.adapter?.itemCount == 0) {
                        setRecyclerData(data)
                    } else { //when load more
                        if (binding.farmerList.adapter == null) { //after load more
                            setRecyclerData(data)
                        }
                        binding.farmerList.adapter?.notifyDataSetChanged()
                    }

    }

    private fun setRecyclerData(photos: ArrayList<Farmer>) {
        with(binding.farmerList) {
            adapter = FarmersAdapter(photos,  {item ->
                callFarmerNow(item)
            }
//                    {
//                details -> detailsOfFarmer(nom, dofb, cardnumber, expirydate, dofbb, givennname,idd, nation, seex, surrname, phonenumber)
//            }
            ) { nom, dofb, cardnumber, expirydate, dofbb, givennname, idd, nation, seex, surrname, phonenumber ->
                detailsOfFarmer(nom, dofb, cardnumber, expirydate, dofbb, givennname, idd, nation, seex, surrname, phonenumber)
            }
        }
    }

    private fun detailsOfFarmer(nom: Any, dofb: Any, cardnumber: Any, expirydate: Any, dofbb: Any, givennname: Any, idd: Any, nation: Any, seex: Any, surrname: Any, phonenumber: Any?) {
        //navigate to details screen
        val f = ArrayList<String>()
        f.add(nom as String)
        f.add(dofb as String)
        f.add(cardnumber as String)
        f.add(expirydate as String)
        f.add(dofbb as String)
        f.add(givennname as String)
        f.add(idd as String)
        f.add(nation as String)
        f.add(seex as String)
        f.add(surrname as String)
        f.add(phonenumber as String)
        Timber.d("details " + f.toString())
        val bundle = bundleOf("details" to f.toString())
        view?.findNavController()?.navigate(R.id.action_farmersListFragment_to_farmerDetailsFragment, bundle)
    }

    private fun callFarmerNow(item: Any) {
        DialogUtils.callFarmer(requireContext(), item.toString())
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
        builder.setTitle("Capture Farmer Data")

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
            val name = customLayout.findViewById<EditText>(R.id.namee)
            val dob = customLayout.findViewById<EditText>(R.id.doob)
            val cardno = customLayout.findViewById<EditText>(R.id.cardnoo)
            val date = customLayout.findViewById<EditText>(R.id.datee)
            val dobb = customLayout.findViewById<EditText>(R.id.doobb)
            val givenname = customLayout.findViewById<EditText>(R.id.givennamme)
            val ID = customLayout.findViewById<EditText>(R.id.IDd)
            val nationality = customLayout.findViewById<EditText>(R.id.nationalityy)
            val sex = customLayout.findViewById<EditText>(R.id.sexxx)
            val surname = customLayout.findViewById<EditText>(R.id.surnamme)
            val phone = customLayout.findViewById<EditText>(R.id.phonne)

            // TODO: 11/29/2020 : find a way to handle empty editext NPE(error)... also different validations like the phone, date validation etc.

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