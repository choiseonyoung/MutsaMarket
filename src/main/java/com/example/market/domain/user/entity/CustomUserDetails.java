package com.example.market.domain.user.entity;

import com.example.market.domain.user.dto.UserSignupDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    @Getter
    private Long id;
    private String username;
    private String password;

    @Getter
    private String phoneNumber;

    @Getter
    private String email;

    @Getter
    private String address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static CustomUserDetails fromEntity(User entity) {
        return CustomUserDetails.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .build();
    }

    public User newEntity() {
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setPhoneNumber(phoneNumber);
        entity.setEmail(email);
        entity.setAddress(address);
        return entity;
    }

    public static UserDetails fromDto(UserSignupDTO dto) {
        return CustomUserDetails.builder().username(dto.getUsername())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}