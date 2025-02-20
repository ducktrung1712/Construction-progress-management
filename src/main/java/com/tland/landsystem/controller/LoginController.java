package com.tland.landsystem.controller;

import com.tland.landsystem.dto.LoginRequest;
import com.tland.landsystem.entity.Users;
import com.tland.landsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Kiểm tra các tham số bắt buộc
        if (loginRequest.getAccount() == null || loginRequest.getAccount().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account is required.");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password is required.");
        }

        System.out.println("Đang kiểm tra login cho: " + loginRequest.getAccount());

        // Tìm người dùng theo username hoặc email
        Users user = usersRepository.findByUsernameOrEmail(loginRequest.getAccount(), loginRequest.getAccount()).orElse(null);

        if (user == null) {
            System.out.println("Tài khoản không tồn tại!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid account.");
        }

        // Nếu bạn sử dụng password encoder thì dùng passwordEncoder.matches(raw, encoded)
        if (user.getPassword() == null || !user.getPassword().equals(loginRequest.getPassword())) {
            System.out.println("Sai mật khẩu!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password.");
        }

        // Xóa mật khẩu trước khi trả về thông tin người dùng
        user.clearPassword();
        return ResponseEntity.ok(user);
    }
}