package com.example.fastfood

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val name = sharedPref.getString("name", "User")
        val email = sharedPref.getString("email", "email@example.com")
        val phone = sharedPref.getString("phone", "N/A")
        val gender = sharedPref.getString("gender", "N/A")

        view.findViewById<TextView>(R.id.tvProfileName).text = name
        view.findViewById<TextView>(R.id.tvProfileEmail).text = email
        view.findViewById<TextView>(R.id.tvProfilePhone).text = phone
        view.findViewById<TextView>(R.id.tvProfileGender).text = gender

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            sharedPref.edit().putBoolean("isLoggedIn", false).apply()

            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}