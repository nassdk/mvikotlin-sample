package com.test.mvikotlin_modosample.feature.global.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.view.MviView
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.test.mvikotlin_modosample.feature.global.ext.mapNotNull
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope
import org.koin.ext.getOrCreateScope

abstract class BaseFragment(layoutRes: Int) : Fragment(layoutRes) {

    open fun prepareUi() {}
    open fun initBindings() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUi()
        initBindings()
    }
//
//    protected val screenScope: Scope = createScreenScope()
//
//    protected inline fun <reified T : BaseFragment> T.createScreenScope(): Scope =
//        this@createScreenScope.getOrCreateScope()
//
//    protected inline fun <reified T> scopeInject(
//        qualifier: Qualifier? = null,
//        noinline parameters: ParametersDefinition? = null
//    ): Lazy<T> = screenScope.inject(qualifier, parameters)

    protected fun <Model : Any, Event : Any, State : Any, Intent : Any>
            MviView<Model, Event>.bind(
        store: Store<Intent, State, *>,
        stateToModel: (State) -> Model,
        eventToIntent: (Event) -> Intent
    ) {
        bind(this@BaseFragment.lifecycle.asMviLifecycle(), BinderLifecycleMode.START_STOP) {
            store.states.mapNotNull(stateToModel) bindTo this@bind
            this@bind.events.mapNotNull(eventToIntent) bindTo store
        }
    }

    protected fun <Model : Any, Event : Any, State : Any, Intent : Any>
            MviView<Model, Event>.bind(
        store: Store<Intent, State, *>,
        viewConnections: ViewConnections<State, Intent, Model, Event>
    ) {
        bind(store, viewConnections.stateToModel, viewConnections.eventToIntent)
    }

    override fun onDestroy() {
//        screenScope.close()
        super.onDestroy()
    }
}