package com.abbas.test.truecaller.presentation.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.abbas.test.truecaller.R
import com.abbas.test.truecaller.databinding.ActivityMainBinding
import com.abbas.test.truecaller.util.MProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainVM by viewModels()
    lateinit var progressDialog: MProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObjects()
        observers()

    }

    private fun observers() {

        // dismiss progress dialog when all three request are served
        viewModel.loading.observe(this) {
            if (it > 0) {
                progressDialog.showLoadingDialog()
            } else if (it <= 0 && progressDialog.isShowingDialog)
                progressDialog.dismissLoadingDialog()
        }

    }

    private fun initObjects() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        progressDialog = MProgressDialog(this)

    }

}