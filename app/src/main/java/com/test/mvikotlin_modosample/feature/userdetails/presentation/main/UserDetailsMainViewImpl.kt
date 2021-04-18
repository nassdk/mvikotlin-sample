package com.test.mvikotlin_modosample.feature.userdetails.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.test.mvikotlin_modosample.databinding.ViewUserDetailsBinding
import com.test.mvikotlin_modosample.feature.userdetails.domain.model.UserDetailsModel
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainView.Event
import com.test.mvikotlin_modosample.feature.userdetails.presentation.main.UserDetailsMainView.Model

class UserDetailsMainViewImpl(
    root: ViewGroup,
    attachToRoot: Boolean = true
) : BaseMviView<Model, Event>() {

    private val viewBinding: ViewUserDetailsBinding =
        ViewUserDetailsBinding.inflate(LayoutInflater.from(root.context), root, attachToRoot)


    override val renderer: ViewRenderer<Model> = diff {
        diff(get = Model::userDetails, set = ::applyUserData)
        diff(get = Model::loading, set = ::setLoading)
    }

    private fun applyUserData(model: UserDetailsModel?) {

        if (model == null) return

        with(viewBinding) {

            login.text = model.login
            name.text = model.name
            company.text = model.company
            image.load(model.avatar) {
                transformations(CircleCropTransformation())
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        viewBinding.progress.visibility = if (loading) View.VISIBLE else View.GONE
    }
}