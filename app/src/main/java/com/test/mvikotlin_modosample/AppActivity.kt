package com.test.mvikotlin_modosample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.modo.*
import com.github.terrakok.modo.android.ModoRender
import com.github.terrakok.modo.android.init
import com.github.terrakok.modo.android.multi.MultiStackFragmentImpl
import com.github.terrakok.modo.android.saveState
import com.test.mvikotlin_modosample.feature.global.navigation.Screens
import org.koin.android.ext.android.inject

class AppActivity : AppCompatActivity() {

    private val modo: Modo by inject()

    private val modoRender by lazy {
        object : ModoRender(this@AppActivity, R.id.appContainer) {
            override fun createMultiStackFragment() = MyMultiStackFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_screen)

        modo.init(savedInstanceState, Screens.MultiStack())
    }

    override fun onResume() {
        super.onResume()
        modo.render = modoRender
    }

    override fun onPause() {
        super.onPause()
        modo.render = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        modo.saveState(outState)
    }

    override fun onBackPressed() = modo.back()

    class MyMultiStackFragment : MultiStackFragmentImpl() {

        private val modo: Modo by inject()

        override fun createTabView(index: Int, parent: LinearLayout): View =
            LayoutInflater.from(context).inflate(R.layout.layout_tab, parent, false).apply {
                findViewById<TextView>(R.id.tabTitle).text = "Tab ${index + 1}"
                setOnClickListener {
                    val currentScreen = modo.state.chain.lastOrNull()
                    if (currentScreen is MultiScreen) {
                        if (currentScreen.selectedStack != index) {
                            modo.selectStack(index)
                        } else {
                            modo.backToTabRoot()
                        }
                    }
                }
            }
    }
}

