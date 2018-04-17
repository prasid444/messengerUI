package com.psyclotron.messenger.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.psyclotron.messenger.R;

public class LoginPage extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText inputPassphrase, inputPassphraseConfirm;
    private TextInputLayout inputLayoutPassphrase, inputLayoutConfirmPassphrase;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputLayoutPassphrase = (TextInputLayout) findViewById(R.id.input_layout_passphrase);
        inputLayoutConfirmPassphrase = (TextInputLayout) findViewById(R.id.input_layout_passphrase_confirm);

        inputPassphrase = (EditText) findViewById(R.id.input_passphrase);
        inputPassphraseConfirm = (EditText) findViewById(R.id.input_passphrase_confirm);

        btnCreateAccount = (Button) findViewById(R.id.btn_create_account);

        inputPassphrase.addTextChangedListener(new MyTextWatcher(inputPassphrase));
        inputPassphraseConfirm.addTextChangedListener(new MyTextWatcher(inputPassphraseConfirm));


        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


    }

    private void submitForm() {
        if (!validatePassphrase()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,Homepage.class);
        startActivity(i);
    }

    private boolean validatePassphrase() {
        if (inputPassphrase.getText().toString().trim().isEmpty()) {
            inputLayoutPassphrase.setError(getString(R.string.err_msg_name));
            requestFocus(inputPassphrase);
            return false;
        } else {

            inputLayoutPassphrase.setErrorEnabled(false);
        }
        if (inputPassphrase.getText().toString().trim() == inputPassphraseConfirm.getText().toString().trim()) {
            Toast.makeText(getApplicationContext(), "Not matching", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
    private boolean validatePassphraseConfirm() {
        if (inputPassphraseConfirm.getText().toString().trim().isEmpty()) {
            inputLayoutConfirmPassphrase.setError(getString(R.string.err_msg_name));
            requestFocus(inputPassphraseConfirm);
            return false;
        } else {

            inputLayoutConfirmPassphrase.setErrorEnabled(false);
        }
        if (inputPassphrase.getText().toString().trim() == inputPassphraseConfirm.getText().toString().trim()) {
            Toast.makeText(getApplicationContext(), "Not matching", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_passphrase:
                    validatePassphrase();
                    break;
                case R.id.input_passphrase_confirm:
                    validatePassphraseConfirm();
                    break;

            }
        }
    }
}



