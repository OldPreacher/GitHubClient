package com.example.githubclient.mvp.presenter

import android.util.Log
import com.example.githubclient.mvp.model.entity.GitHubUser
import com.example.githubclient.mvp.model.repositiry.IGitHubUsersRepository
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.mvp.view.list.UserItemView
import com.example.githubclient.navigation.AndroidScreens
import com.example.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepository: IGitHubUsersRepository,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let {
                view.setLogin(it)
            }
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UserListPresenter()
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(AndroidScreens().userDetails(user))
        }
    }

    private fun loadData() {
        val disposable = usersRepository.getUsers()
            .observeOn(uiScheduler)
            .subscribe ({ users ->
                Log.d("UsersPresenter", "Users loaded: $users")
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, { error ->
                Log.e("UsersPresenter", "Error loading users", error)
            })

        disposables.add(disposable)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
