package vn.edu.utt.uttqlsv.view.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.utt.uttqlsv.R;
import vn.edu.utt.uttqlsv.controller.SignUpController;
import vn.edu.utt.uttqlsv.model.Gender;

public class SignUp extends AppCompatActivity {
    private Spinner spinner;
    private ArrayAdapter adapter;
    private EditText usernameTV;
    private EditText passwordTV;
    private EditText rePasswordTV;
    private EditText emailTV;
    private EditText phoneTV;
    private EditText addressTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        spinner = findViewById(R.id.gender_spinner);
        usernameTV = findViewById(R.id.username_edt);
        passwordTV = findViewById(R.id.password_edt);
        rePasswordTV = findViewById(R.id.re_password_edt);
        emailTV = findViewById(R.id.email_edt);
        phoneTV = findViewById(R.id.phone_edt);
        addressTV = findViewById(R.id.address_edt);
        Button signUpBtn = findViewById(R.id.sign_up_btn);

        //Init gender picker dropdown menu
        initGenderPickerMenu();

        //Handle sign up btn
        //get string from all edit text field then request for signing up
        signUpBtn.setOnClickListener(v -> signUpRequest());
    }

    private void initGenderPickerMenu() {
        String[] items = {"Male", "Female"};
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    private void signUpRequest() {
        //Get all string from edit text field
        String username = usernameTV.getText().toString().trim();
        String password = passwordTV.getText().toString().trim();
        String rePassword = rePasswordTV.getText().toString().trim();
        String email = emailTV.getText().toString().trim();
        String phone = phoneTV.getText().toString().trim();
        String address = addressTV.getText().toString().trim();
        boolean gender = (spinner.getSelectedItemId() == 0) ? Gender.MALE : Gender.FEMALE;

        //request for register
        if (SignUpController.signUp(this, username, password, rePassword, email, phone, gender, address)) {
            finish();
        }
    }
}
