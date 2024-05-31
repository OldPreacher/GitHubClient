package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubclient.databinding.FragmentUserDetailsBinding
import com.example.githubclient.mvp.model.GitHubUser

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_USER = "user"

        fun newInstance(user: GitHubUser) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_USER, user)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getParcelable<GitHubUser>(ARG_USER)
        binding.tvUserLogin.text = user?.login
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}