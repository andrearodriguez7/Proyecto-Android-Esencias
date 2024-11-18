package com.example.esencias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.expandablelayout.ExpandableLayout


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GestionVelas : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_gestion_velas, container, false)
        val expandableLayout = view.findViewById<ExpandableLayout>(R.id.expandableLayout)

        expandableLayout.setOnClickListener {
            if (expandableLayout.isExpanded) {
                expandableLayout.collapse()
            } else {
                expandableLayout.expand()
            }
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GestionVelas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}