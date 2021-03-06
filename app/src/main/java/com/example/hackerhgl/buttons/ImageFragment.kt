package com.example.hackerhgl.buttons

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.relex.circleindicator.CircleIndicator
import java.util.*
import com.example.hackerhgl.buttons.R.string.images




/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ImageFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: SlideShowAdapter
    private lateinit var mView: View
    private lateinit var indicator: CircleIndicator
    private lateinit var runnable: Runnable
    private var timer: Timer = Timer()
    private var handler: Handler = Handler()

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments?.getString(ARG_PARAM1)
            mParam2 = arguments?.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView =  inflater!!.inflate(R.layout.fragment_image, container, false)
        viewPager = mView.findViewById(R.id.viewpager)
        indicator = mView.findViewById(R.id.circle_indicator)
        adapter = SlideShowAdapter(context!!)
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)


        runnable = Runnable {
            var i = viewPager.currentItem
            if (i == adapter.images.size - 1) {
                i = 0
                viewPager.setCurrentItem(i, true)
            } else {
                i++
                viewPager.setCurrentItem(i, true)
            }
        }

        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 4000, 4000)


        return  mView
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
         * @return A new instance of fragment ImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
