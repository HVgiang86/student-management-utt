package vn.edu.utt.uttqlsv.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.utt.uttqlsv.R;
import vn.edu.utt.uttqlsv.controller.LoginController;

public class Login extends AppCompatActivity {
    private boolean isVisiblePassword = false;
    private ImageView showPasswordBtn;
    private EditText passwordEdt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showPasswordBtn = findViewById(R.id.login_in_activity_show_password_btn);
        passwordEdt = findViewById(R.id.login_activity_password_edt);
        EditText usernameEdt = findViewById(R.id.login_activity_username_edt);
        EditText passwordEdt = findViewById(R.id.login_activity_password_edt);

        //Load saved account from Shared Preferences
        LoginController.loadStoredAccount(this);

        //Handle show password button
        //switch between visible and invisible password
        showPasswordBtn.setOnClickListener(v -> {
            if (isVisiblePassword) setPasswordInvisible();
            else setPasswordVisible();
        });

        findViewById(R.id.login_btn).setOnClickListener(v -> {
            String username = usernameEdt.getText().toString().trim();
            String password = passwordEdt.getText().toString().trim();

            if (!username.isEmpty() && !password.isEmpty()) {
                if (LoginController.login(this, username, password))
                    passwordEdt.setText("");
            }
        });

        findViewById(R.id.login_activity_sign_up_text_btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

    }

    private void setPasswordVisible() {
        showPasswordBtn.setImageResource(R.drawable.ic_visibility_off);
        passwordEdt.setTransformationMethod(null);
        isVisiblePassword = true;
    }

    private void setPasswordInvisible() {
        showPasswordBtn.setImageResource(R.drawable.ic_visibility);
        passwordEdt.setTransformationMethod(new PasswordTransformationMethod());
        isVisiblePassword = false;
    }


}
