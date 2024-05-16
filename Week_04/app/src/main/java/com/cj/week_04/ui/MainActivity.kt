package com.cj.week_04.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.cj.week_04.R
import com.cj.week_04.databinding.ActivityMainBinding
import com.cj.week_04.models.UserTypeModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var view: ActivityMainBinding

    var email = MutableLiveData("")
    var password = MutableLiveData("")
    var checkPassword = MutableLiveData("")
    var name = MutableLiveData("")
    var phone = MutableLiveData("")

    var selectedBirthday = MutableLiveData("")
    var selectedUserType = MutableLiveData(UserTypeModel.STUDENT)

    var isAcceptEULA = MutableLiveData(false)
    var isAcceptPrivacyLicense = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.activity_main)
        view.view = this
        view.lifecycleOwner = this

        selectedBirthday.value = resources.getString(R.string.TXT_NO_BIRTHDAY_SELECTED)
    }

    fun onClick(v: View) {
        when (v) {
            view.btnDatePicker -> {
                MaterialDatePicker.Builder.datePicker()
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                    .also { dialog ->
                        dialog.show(supportFragmentManager, dialog.toString())

                        dialog.addOnPositiveButtonClickListener { date ->
                            selectedBirthday.value = convertLongToTime(date)
                        }
                    }
            }

            view.btnSignUp -> {
                if (checkEmptyField()) {
                    showAlertDialog(resources.getString(R.string.TXT_TITLE_OF_EMPTY_FIELD), resources.getString(R.string.TXT_CONTENTS_OF_EMPTY_FIELD))
                } else if(!isAcceptEULA.value!! || !isAcceptPrivacyLicense.value!!){
                    showAlertDialog(resources.getString(R.string.TXT_TITLE_OF_NOT_ACCEPTED_LICENSE), resources.getString(R.string.TXT_CONTENTS_OF_NOT_ACCEPTED_LICENSE))
                } else if(!email.value!!.contains("@")){
                    showAlertDialog(resources.getString(R.string.TXT_TITLE_OF_INCORRECT_EMAIL_TYPE), resources.getString(R.string.TXT_CONTENTS_OF_INCORRECT_EMAIL_TYPE))
                } else if(password.value!!.length < 8){
                    showAlertDialog(resources.getString(R.string.TXT_TITLE_OF_INCORRECT_PASSWORD), resources.getString(R.string.TXT_CONTENTS_OF_INCORRECT_PASSWORD))
                } else if(password.value!! != checkPassword.value!!){
                    showAlertDialog(resources.getString(R.string.TXT_TITLE_OF_PASSWORD_MISMATCH), resources.getString(R.string.TXT_CONTENTS_OF_PASSWORD_MISMATCH))
                } else{
                    MaterialAlertDialogBuilder(this)
                        .setIcon(ResourcesCompat.getDrawable(resources, R.drawable.rounded_check_circle_24, null))
                        .setTitle(resources.getString(R.string.TXT_SIGNUP))
                        .setMessage("${resources.getString(R.string.TXT_CONTENTS_OF_CONFIRM_SIGN_UP)}\n" +
                                "${resources.getString(R.string.TXT_EMAIL)} : ${email.value}\n" +
                                "${resources.getString(R.string.TXT_NAME)} : ${name.value}\n" +
                                "${resources.getString(R.string.TXT_PHONE)} : ${phone.value}\n" +
                                "${resources.getString(R.string.TXT_BIRTHDAY)} : ${selectedBirthday.value}\n" +
                                "${resources.getString(R.string.TXT_USER_TYPE)} : ${selectedUserType.value!!.getString()}"
                        )

                        .setPositiveButton(resources.getString(R.string.TXT_YES)) { dialog, _ ->
                            view.btnSignUp.visibility = View.GONE
                            view.progressView.visibility = View.VISIBLE

                            dialog.dismiss()
                        }

                        .setNegativeButton(resources.getString(R.string.TXT_NO)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }

            view.btnStudent -> selectedUserType.value = UserTypeModel.STUDENT
            view.btnProfessor -> selectedUserType.value = UserTypeModel.PROFESSOR
            view.btnEmployee -> selectedUserType.value = UserTypeModel.EMPLOYEE
        }
    }

    private fun getString(): String{
        return if(selectedUserType.value == UserTypeModel.STUDENT){
            "학생"
        } else if(selectedUserType.value == UserTypeModel.EMPLOYEE){
            "교직원"
        } else{
            "교수"
        }
    }

    private fun getStringBySelectedRadioButton(): String{
        return if(view.btnStudent.isChecked){
            "학생"
        } else if(view.btnEmployee.isChecked){
            "교직원"
        } else{
            "교수"
        }
    }

    private fun showAlertDialog(title: String, contents: String){
        val alertDialog = MaterialAlertDialogBuilder(this)

        alertDialog.setIcon(ResourcesCompat.getDrawable(resources, R.drawable.round_warning_24, null))
        alertDialog.setTitle(title)
        alertDialog.setMessage(contents)

        alertDialog.setPositiveButton(
            resources.getString(R.string.TXT_OK)
        ) { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.show()
    }

    private fun checkEmptyField(): Boolean {
        return (email.value!!.isEmpty() || password.value!!.isEmpty() || checkPassword.value!!.isEmpty() || name.value!!.isEmpty() || phone.value!!.isEmpty() ||
                selectedBirthday.value.toString().isEmpty() || selectedBirthday.value.toString() == resources.getString(R.string.TXT_NO_BIRTHDAY_SELECTED))
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy. MM. dd", Locale.KOREA)
        return format.format(date)
    }
}