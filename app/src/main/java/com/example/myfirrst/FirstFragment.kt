package com.example.myfirrst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lottie3.setAnimation("gradback.json")
        lottie3.playAnimation()
        lottie3.loop(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      val v =    inflater.inflate(R.layout.fragment_first, container, false)


        val bt = v.findViewById<Button>(R.id.metal1)



       bt.setOnClickListener {
           val secondFragment = SecondFragment()
           val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
           transaction.replace(R.id.linearLayout,secondFragment)
           transaction.commit()
       }
        return v

    }


}