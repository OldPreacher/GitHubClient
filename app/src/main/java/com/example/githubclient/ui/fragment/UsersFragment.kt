package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.MyApp
import com.example.githubclient.databinding.FragmentUsersBinding
import com.example.githubclient.mvp.model.api.ApiHolder
import com.example.githubclient.mvp.model.repositiry.retrofit.RetrofitGitHubUsersRepository
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.ui.activity.BackButtonListener
import com.example.githubclient.ui.adapter.UsersRVAdapter
import com.example.githubclient.ui.image.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: UsersRVAdapter? = null
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGitHubUsersRepository(ApiHolder.api),
            MyApp.instance.router,
            MyApp.instance.screens
        )
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("UsersFragment", "onCreateView")
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("UsersFragment", "onDestroyView")
        _binding = null
    }

    override fun init() {
        Log.d("UsersFragment", "init")
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        Log.d("UsersFragment", "updateList")
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        Log.d("UsersFragment", "backPressed")
        return presenter.backPressed()
    }
}