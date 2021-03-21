package com.example.rentaplace.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide.init
import com.example.rentaplace.R
import com.example.rentaplace.data.model.User
import com.example.rentaplace.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment() {

    lateinit var fragmentTitle: String
    lateinit var viewModel: RegisterViewModel

    lateinit var registerBinding : FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        var view = registerBinding.root
        init(view)
        return view
    }

    private fun init(view: View) {
         viewModel = ViewModelProvider(activity!!).get(RegisterViewModel::class.java)
        registerBinding.lifecycleOwner = this
        registerBinding.registerViewModel = viewModel

        viewModel.registrationStatus.observe(viewLifecycleOwner, Observer {
            if(it == RegisterViewModel.RegisterAction.SUCCESS)
            {
                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(activity, LoginActivity::class.java))
                activity!!.finish()
            }
        })

        if(fragmentTitle == AccountTypes.Tenant.name)
        {
            view.register_edit_text_landlord.visibility = View.VISIBLE
        }

        view.register_page_redirect_button.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
            activity!!.finish()
        }

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if( isVisibleToUser && isResumed)
        {
            viewModel.errorMessage.value = ""
            viewModel.user = User()
            viewModel.confirmPassword = ""
            viewModel.user.type = fragmentTitle.toLowerCase()
            Log.d("abc", viewModel.user.type.toString())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(title: String) =
            RegisterFragment().apply {
                fragmentTitle = title
            }
    }
}