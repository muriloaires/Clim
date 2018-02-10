package br.com.airescovit.clim.di.component

import br.com.airescovit.clim.di.PerActivity
import br.com.airescovit.clim.di.module.ActivityModule
import br.com.airescovit.clim.ui.addclients.AddClientsActivity
import br.com.airescovit.clim.ui.addtask.AddTaskActivity
import br.com.airescovit.clim.ui.clients.ClientsFragment
import br.com.airescovit.clim.ui.login.LoginActivity
import br.com.airescovit.clim.ui.login.login.LoginFragment
import br.com.airescovit.clim.ui.login.register.RegisterFragment
import br.com.airescovit.clim.ui.main.MainActivity
import br.com.airescovit.clim.ui.splash.Splash
import br.com.airescovit.clim.ui.tasks.TasksFragment
import dagger.Component

/**
 * Created by Logics on 12/01/2018.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent : ApplicationComponent {

    fun inject(splash: Splash)

    fun inject(loginActivity: LoginActivity)

    fun inject(addClientsActivity: AddClientsActivity)

    fun inject(addTaskActivity: AddTaskActivity)

    fun inject(registerFragment: RegisterFragment)

    fun inject(loginFragment: LoginFragment)

    fun inject(clientsFragment: ClientsFragment)

    fun inject(tasksFragment: TasksFragment)

    fun inject(mainActivity: MainActivity)

}