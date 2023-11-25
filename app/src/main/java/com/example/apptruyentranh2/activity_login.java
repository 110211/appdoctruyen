package com.example.apptruyentranh2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class activity_login extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Ánh xạ các thành phần UI
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(view -> loginUser());

        // Button để chuyển đến RegisterActivity
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button buttonGoToRegister = findViewById(R.id.buttonGoToRegister);

        buttonGoToRegister.setOnClickListener(view -> {
            Intent intent = new Intent(activity_login.this, activity_register.class);
            intent.putExtra("from-login", true);
            startActivity(intent);
            finish();
        });
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Kiểm tra hợp lệ của email và password
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ email và mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Đăng nhập bằng Firebase Authentication
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Đăng nhập thành công, xử lý sau khi đăng nhập
                        handleLoginSuccess();
                    } else {
                        // Đăng nhập thất bại, xử lý thông báo lỗi
                        Toast.makeText(activity_login.this, "Đăng nhập thất bại: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleLoginSuccess() {
        // Xử lý sau khi đăng nhập thành công,  chuyển đến màn hình chính
        Intent intent = new Intent(activity_login.this, MainActivity.class);
       startActivity(intent);
        finish();
    }
}
