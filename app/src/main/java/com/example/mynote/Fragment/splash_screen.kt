package com.example.mynote.Fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.mynote.R
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*


class splash_screen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        view.mainLayoutSplash.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.anim_alpha
            )
        )
        view.imgSplash.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.anim_left_to_right
            )
        )
        view.tvSplash.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.anim_right_to_left
            )
        )

        Handler().postDelayed({
            Navigation.findNavController(view).navigate(R.id.main_screen)
        }, 2000)
        return view
    }
}