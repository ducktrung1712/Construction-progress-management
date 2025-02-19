package com.tland.landsystem.controller;

import com.tland.landsystem.Entity.Users;
import com.tland.landsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    // Lấy danh sách tất cả người dùng
    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Lấy thông tin người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo người dùng mới (với kiểm tra tồn tại email và username)
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        // Kiểm tra xem email đã tồn tại hay chưa
        if (usersRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email đã tồn tại, vui lòng chọn email khác.");
        }

        // Kiểm tra xem username đã tồn tại hay chưa
        if (usersRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username đã tồn tại, vui lòng chọn username khác.");
        }

        // Nếu không tồn tại, lưu người dùng mới
        Users newUser = usersRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users userDetails) {
        return usersRepository.findById(id).map(user -> {
            // Bạn có thể thêm kiểm tra trùng lặp email/username nếu cần ở đây
            user.setFullName(userDetails.getFullName());
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setWorkGroup(userDetails.getWorkGroup());
            Users updatedUser = usersRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Xóa người dùng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        return usersRepository.findById(id).map(user -> {
            usersRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
