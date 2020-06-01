package com.example.fichaexercicios.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fichaexercicios.R
import com.example.fichaexercicios.ui.fragments.CalculatorFragment
import com.example.fichaexercicios.ui.fragments.HistoryFragment
import com.example.fichaexercicios.ui.fragments.MapFragment

class NavigationManager {

    companion object{

        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToCalculatorFragment(fm: FragmentManager){
            placeFragment(
                fm,
                CalculatorFragment()
            )
        }

        fun goToHistoryFragment(fm: FragmentManager){
            placeFragment(
                fm,
                HistoryFragment()
            )
        }

        fun goToMapFragment(fm: FragmentManager){
            placeFragment(
                fm,
                MapFragment()
            )
        }

    }

}