package com.cj.week_05.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.cj.week_05.R
import com.cj.week_05.databinding.LayoutSigninBinding
import com.cj.week_05.helper.showAlertDialog

class SignInView: Fragment() {
    private lateinit var view: LayoutSigninBinding

    var email = MutableLiveData("")
    var password = MutableLiveData("")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        view = DataBindingUtil.inflate(inflater, R.layout.layout_signin, container, false)
        view.view = this
        view.lifecycleOwner = this

        return view.root
    }

    fun onClick(v: View){
        val transactionManager = (context as StartActivity).supportFragmentManager.beginTransaction()

        when(v){
            view.btnSignIn -> {
                if(email.value == "" || password.value == ""){
                    showAlertDialog(context as StartActivity, resources.getString(R.string.TXT_TITLE_OF_EMPTY_FIELD), resources.getString(R.string.TXT_CONTENTS_OF_EMPTY_FIELD))
                }
            }

            view.btnSignUp -> {
                transactionManager.addToBackStack("SignInView")
                transactionManager.replace(R.id.frameLayout, SignUpView()).commit()
            }

            view.btnResetPassword -> {
                transactionManager.addToBackStack("SignInView")
                transactionManager.replace(R.id.frameLayout, ResetPasswordView()).commit()
            }
        }
    }
}