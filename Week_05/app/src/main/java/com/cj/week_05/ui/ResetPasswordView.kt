package com.cj.week_05.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.cj.week_05.R
import com.cj.week_05.databinding.LayoutResetPasswordBinding
import com.cj.week_05.helper.showAlertDialog

class ResetPasswordView: Fragment() {
    private lateinit var view: LayoutResetPasswordBinding
    var email = MutableLiveData("")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.layout_reset_password, container, false)
        view.view = this
        view.lifecycleOwner = this

        return view.root
    }

    fun onClick(v: View){
        when(v){
            view.topAppBar -> {
                val fragmentManager = (context as StartActivity).supportFragmentManager
                fragmentManager.popBackStack()
            }

            view.btnResetPassword -> {
                if(email.value == ""){
                    if(email.value == ""){
                        showAlertDialog(context as StartActivity, resources.getString(R.string.TXT_TITLE_OF_EMPTY_FIELD), resources.getString(R.string.TXT_CONTENTS_OF_EMPTY_FIELD))
                    }
                }
            }
        }
    }
}