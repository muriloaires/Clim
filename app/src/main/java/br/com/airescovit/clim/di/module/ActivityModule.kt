package br.com.airescovit.clim.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import br.com.airescovit.clim.di.ActivityContext
import br.com.airescovit.clim.di.PerActivity
import br.com.airescovit.clim.ui.addtask.AddTaskMvpPresenter
import br.com.airescovit.clim.ui.addtask.AddTaskMvpView
import br.com.airescovit.clim.ui.addtask.AddTaskPresenter
import br.com.airescovit.clim.ui.clients.ClientsMvpPresenter
import br.com.airescovit.clim.ui.clients.ClientsMvpView
import br.com.airescovit.clim.ui.clients.ClientsPresenter
import br.com.airescovit.clim.ui.clients.addclients.AddClientsMvpPresenter
import br.com.airescovit.clim.ui.clients.addclients.AddClientsMvpView
import br.com.airescovit.clim.ui.clients.addclients.AddClientsPresenter
import br.com.airescovit.clim.ui.clients.selectclient.SelectClientMvpPresenter
import br.com.airescovit.clim.ui.clients.selectclient.SelectClientMvpView
import br.com.airescovit.clim.ui.clients.selectclient.SelectClientPresenter
import br.com.airescovit.clim.ui.login.LoginMvpPresenter
import br.com.airescovit.clim.ui.login.LoginMvpView
import br.com.airescovit.clim.ui.login.LoginPresenter
import br.com.airescovit.clim.ui.login.login.LoginFragmentMvpPresenter
import br.com.airescovit.clim.ui.login.login.LoginFragmentMvpView
import br.com.airescovit.clim.ui.login.login.LoginFragmentPresenter
import br.com.airescovit.clim.ui.login.register.RegisterMvpPresenter
import br.com.airescovit.clim.ui.login.register.RegisterMvpView
import br.com.airescovit.clim.ui.login.register.RegisterPresenter
import br.com.airescovit.clim.ui.main.MainMvpPresenter
import br.com.airescovit.clim.ui.main.MainMvpView
import br.com.airescovit.clim.ui.main.MainPresenter
import br.com.airescovit.clim.ui.main.adapter.PagerAdapter
import br.com.airescovit.clim.ui.splash.SplashMvpPresenter
import br.com.airescovit.clim.ui.splash.SplashMvpView
import br.com.airescovit.clim.ui.splash.SplashPresenter
import br.com.airescovit.clim.ui.tasks.TaskMvpPresenter
import br.com.airescovit.clim.ui.tasks.TaskPresenter
import br.com.airescovit.clim.ui.tasks.TasksMvpView
import dagger.Module
import dagger.Provides

/**
 * Created by Logics on 12/01/2018.
 */
@Module
class ActivityModule(activity: AppCompatActivity) {
    var mActivity: AppCompatActivity = activity

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }


    @PerActivity
    @Provides
    fun provideSplashPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> {
        return presenter
    }

    @PerActivity
    @Provides
    fun provideMainPresenter(mainPresenter: MainPresenter<MainMvpView>): MainMvpPresenter<MainMvpView> {
        return mainPresenter
    }

    @PerActivity
    @Provides
    fun provideAddClientsPresenter(addClientsPresenter: AddClientsPresenter<AddClientsMvpView>): AddClientsMvpPresenter<AddClientsMvpView> {
        return addClientsPresenter
    }

    @PerActivity
    @Provides
    fun provideLoginPresenter(presenter: LoginPresenter<LoginMvpView>): LoginMvpPresenter<LoginMvpView> {
        return presenter
    }

    @Provides
    fun provideLoginFragmentPresenter(loginFragmentPresenter: LoginFragmentPresenter<LoginFragmentMvpView>): LoginFragmentMvpPresenter<LoginFragmentMvpView> {
        return loginFragmentPresenter
    }

    @Provides
    fun provideRegisterPresenter(registerPresenter: RegisterPresenter<RegisterMvpView>): RegisterMvpPresenter<RegisterMvpView> {
        return registerPresenter
    }

    @Provides
    fun provideClientsPresenter(clientsPresenter: ClientsPresenter<ClientsMvpView>): ClientsMvpPresenter<ClientsMvpView> {
        return clientsPresenter
    }

    @PerActivity
    @Provides
    fun provideSelectClientsPresenter(mSelectClientPresenter: SelectClientPresenter<SelectClientMvpView>): SelectClientMvpPresenter<SelectClientMvpView> {
        return mSelectClientPresenter
    }

    @Provides
    fun providePagerAdapter(activity: AppCompatActivity): PagerAdapter {
        return PagerAdapter(activity.supportFragmentManager, 2)
    }

    @Provides
    fun provideTaskPresenter(taskPresenter: TaskPresenter<TasksMvpView>): TaskMvpPresenter<TasksMvpView> {
        return taskPresenter
    }

    @PerActivity
    @Provides
    fun provideAddTaskPresenter(addTaskPresenter: AddTaskPresenter<AddTaskMvpView>): AddTaskMvpPresenter<AddTaskMvpView> {
        return addTaskPresenter
    }


}