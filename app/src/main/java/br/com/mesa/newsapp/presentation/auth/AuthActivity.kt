package br.com.mesa.newsapp.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.mesa.newsapp.data.model.Auth
import br.com.mesa.newsapp.databinding.ActivityAuthBinding
import br.com.mesa.newsapp.presentation.home.HomeActivity
import br.com.mesa.newsapp.util.Preferences
import org.koin.android.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load()
    }

    private fun load() {
        binding.editTextLoginEmail.setText("john@doe.com")
        binding.editTextLoginPassword.setText("123456")

        authViewModel.token.observe(this, Observer {
           Preferences(this).save(Preferences.USER_TOKEN,it)
            startActivity(Intent(this, HomeActivity::class.java))
        })

        binding.buttonIn.setOnClickListener {
            authViewModel.getAuth(
                Auth(
                    binding.editTextLoginEmail.text.toString(),
                    binding.editTextLoginPassword.text.toString()
                )
            )
        }

        authViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                binding.progressLogin.visibility = View.VISIBLE
            } else {
                binding.progressLogin.visibility = View.INVISIBLE
            }
        })

        authViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }
}