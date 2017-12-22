package com.example.hackerhgl.buttons

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.AppCompatRadioButton
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mHorizontalBar: ProgressBar
    private lateinit var mSwitch: SwitchCompat
    private lateinit var mCheckbox: AppCompatCheckBox
    private lateinit var mRadioGroup: RadioGroup
    private lateinit var mAlertDialog: AlertDialog
    private lateinit var mAlertBuilder: AlertDialog.Builder
    private lateinit var mConfirmationDialog: AlertDialog
    private lateinit var mConfirmationBuilder: AlertDialog.Builder
    private lateinit var mProgressDialog: ProgressDialog
    private lateinit var mSnackbar: Snackbar
    private lateinit var mView: View


    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_home, container, false)

        initProgressBar()
        initButtons()
        initSwitch()
        initCheckbox()
        initRadio()
        initAlert()
        initConfirmation()
        initProgressDialog()
        initSnackBar()

        return mView
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    private fun initSnackBar() {
        val mainView = mView.findViewById<ScrollView>(R.id.main_activity_scroll_view)
        val snackbarButton = mView.findViewById<AppCompatButton>(R.id.snack_bar_button)
        snackbarButton.setOnClickListener({ _ ->
            mSnackbar.show()
        })
        mSnackbar = Snackbar.make(mainView, "Hello this is Snackbar", Snackbar.LENGTH_SHORT)
        mSnackbar.setAction("Hide", {_ ->
            mSnackbar.dismiss()
        })
        val view = mSnackbar.view
        val text = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        val actionText = view.findViewById<TextView>(android.support.design.R.id.snackbar_action)

        text.setTextColor(resources.getColor(R.color.seeGreen))
        actionText.setTextColor(resources.getColor(R.color.purple))

    }


    private fun initProgressDialog() {
        val progressButton = mView.findViewById<AppCompatButton>(R.id.progress_dialog_button)
        progressButton .setOnClickListener { _ ->
            run {
                mProgressDialog.show()
            }
        }

        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setTitle("Progress Dialog")
        mProgressDialog.setMessage("Please wait! ...")

    }


    private fun initConfirmation() {
        val items = arrayOf("easy", "normal", "difficult", "very difficult")
        var result = ""
        val confirmationButton = mView.findViewById<AppCompatButton>(R.id.confirm_button)
        confirmationButton.setOnClickListener { _ ->
            run {
                mConfirmationDialog.show()
            }
        }
        mConfirmationBuilder = AlertDialog.Builder(activity)
        mConfirmationBuilder.setTitle("May i Confirm  ?")
        mConfirmationBuilder.setSingleChoiceItems(items, 0, { _, i ->
            result = items[i]
        })
        mConfirmationBuilder.setPositiveButton("Ok", {_, _ ->
            Toast.makeText(activity.applicationContext, "Selected : "+ result, Toast.LENGTH_SHORT).show()
        })
        mConfirmationBuilder.setNegativeButton("Cancel", {_, _ -> {}})


        mConfirmationDialog = mConfirmationBuilder.create()

    }

    private fun initAlert() {
        val alertButton = mView.findViewById<AppCompatButton>(R.id.alert_button)
        alertButton.setOnClickListener { _ ->
            run { mAlertDialog.show() }
        }
        mAlertBuilder = AlertDialog.Builder(activity, R.style.AlertDialogTheme)
        mAlertBuilder.setMessage("May i Discard  ?")
        mAlertBuilder.setPositiveButton("Discard", { _, _ ->
            Toast.makeText(activity.applicationContext, "Discard Button", Toast.LENGTH_SHORT).show()
        })

        mAlertBuilder.setPositiveButton("Discard 2", { _, _ ->
            Toast.makeText(activity.applicationContext, "Discard 2 Button", Toast.LENGTH_SHORT).show()
        })

        mAlertBuilder.setNegativeButton("Cancel", { _, _ ->
            Toast.makeText(activity.applicationContext, "CancelButton", Toast.LENGTH_SHORT).show()
        })

        mAlertDialog = mAlertBuilder.create()

    }

    private fun initRadio() {
        mRadioGroup = mView.findViewById(R.id.radio_group)

        mRadioGroup.setOnCheckedChangeListener { _, id ->
            run {
                val id = mView.findViewById<AppCompatRadioButton>(id)
                val text = id.text
                val status  = id.isChecked
                Toast.makeText(activity.applicationContext,  "" + text + " : " + status, Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun initCheckbox() {
        mCheckbox = mView.findViewById(R.id.checkbox)

        mCheckbox.setOnCheckedChangeListener { _, checked ->
            Toast.makeText(activity.applicationContext, "Checkbox status : " + checked.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initButtons() {
        val raisedButton: AppCompatButton = mView.findViewById(R.id.raised_button)
        val flatButton: AppCompatButton = mView.findViewById(R.id.flat_button)
        val fabButton: FloatingActionButton = mView.findViewById(R.id.fab_btn)
        raisedButton.setOnClickListener {
            if (mProgressBar.visibility == View.VISIBLE)
                mProgressBar.visibility = View.GONE
            else
                mProgressBar.visibility = View.VISIBLE
        }
        flatButton.setOnClickListener {
            if (mHorizontalBar.visibility == View.VISIBLE)
                mHorizontalBar.visibility = View.GONE
            else
                mHorizontalBar.visibility = View.VISIBLE
        }
        fabButton.setOnClickListener {
            Toast.makeText(activity.applicationContext, "Fab Button", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initProgressBar() {
        mProgressBar = mView.findViewById(R.id.progress_bar)
        mProgressBar.visibility = View.VISIBLE

        mHorizontalBar = mView.findViewById(R.id.horizontal_bar)
        mHorizontalBar.visibility = View.VISIBLE
    }

    private fun initSwitch() {
        mSwitch = mView.findViewById(R.id.switch_compat)

        mSwitch.setOnCheckedChangeListener { _, status ->
            Toast.makeText(activity.applicationContext,"Status : " + status.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}// Required empty public constructor
