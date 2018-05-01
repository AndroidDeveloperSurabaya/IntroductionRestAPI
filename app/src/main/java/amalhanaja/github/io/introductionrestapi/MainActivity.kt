package amalhanaja.github.io.introductionrestapi

import amalhanaja.github.io.introductionrestapi.service.UserService
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private val disposables = CompositeDisposable()

    private val userService by lazy {
        UserService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSignUp.setOnClickListener {
            doRegister(edt_email.text.toString(), edt_full_name.text.toString(), edt_password.text.toString())
        }
    }

    fun doRegister(email: String, fullName: String, password: String){
        showLoading()
        disposables.add(
                userService.register(UserService.Params.Register(password, fullName, email))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it.isSuccessful) {
                                tv_response.text = it.body()?.token
                            } else {
                                tv_response.text = it.code().toString()
                            }
                            hideLoading()
                        })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
        btnSignUp.isEnabled = false
        edt_email.isEnabled = false
        edt_full_name.isEnabled = false
        edt_password.isEnabled = false
    }

    fun hideLoading(){
        progressBar.visibility = View.GONE
        btnSignUp.isEnabled = true
        edt_email.isEnabled = true
        edt_full_name.isEnabled = true
        edt_password.isEnabled = true
    }
}
