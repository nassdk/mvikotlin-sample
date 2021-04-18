package com.test.mvikotlin_modosample.feature.global.ext

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.coroutines.flow.Flow
import org.koin.core.definition.Definition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.ScopeDSL
import org.koin.dsl.onClose
import kotlinx.coroutines.flow.mapNotNull as mapNotNullFlow


internal inline fun <T, R : Any> Flow<T>.mapNotNull(crossinline mapper: (T) -> R?): Flow<R> =
    mapNotNullFlow { mapper(it) }

inline fun <reified STORE : Store<*, *, *>>
        ScopeDSL.store(
    qualifier: Qualifier? = null,
    override: Boolean = false,
    noinline definition: Definition<STORE>
) = scoped(qualifier, override, definition).onClose { it?.dispose() }

inline fun <T : Fragment> T.withArgs(
    argsBuilder: Bundle.() -> Unit
): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
