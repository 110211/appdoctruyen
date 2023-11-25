package com.example.apptruyentranh2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;

public class activity_register extends AppCompatActivity {

    private TextView editTextUsername, editTextEmail, editTextPassword;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(view -> registerUser());

        // Kiểm tra xem có thông tin từ activity_login không
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("fromLogin")) {
            finish(); // chỉ kết thúc nếu được gọi từ activity_login
        }
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Kiểm tra hợp lệ của thông tin đăng ký
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Đăng ký bằng Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Đăng ký thành công, xử lý sau khi đăng ký
                        Toast.makeText(activity_register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        finish(); // Kết thúc màn hình đăng ký và quay lại màn hình đăng nhập hoặc màn hình chính
                    } else {
                        // Đăng ký thất bại, xử lý thông báo lỗi
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(activity_register.this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity_register.this, "Đăng ký thất bại: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
