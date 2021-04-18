package com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.test.mvikotlin_modosample.databinding.ViewUserDetailsToolbarBinding
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarView.Event
import com.test.mvikotlin_modosample.feature.userdetails.presentation.toolbar.UserDetailsToolbarView.Model

class UserDetailsToolbarViewImpl(
    root: ViewGroup,
    attachToParent: Boolean = true
) : BaseMviView<Model, Event>() {

    private val viewBinding: ViewUserDetailsToolbarBinding = ViewUserDetailsToolbarBinding.inflate(
        LayoutInflater.from(root.context), root, attachToParent
    )

    init {
        viewBinding.root.setNavigationOnClickListener {
            dispatch(event = Event.OnBackPressed)
        }
    }

    override val renderer: ViewRenderer<Model> = diff {
        diff(get = Model::login) { title ->
            viewBinding.root.title = title
        }
    }
}