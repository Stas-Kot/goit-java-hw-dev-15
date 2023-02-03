package com.goit.feature.mvc.user;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = getByIdOrNull(username);
        System.out.println("userData = " + userData.toString());

        if (userData == null) {
            throw new UsernameNotFoundException(username + " not found!");
        }

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton((GrantedAuthority) userData::getRole);
            }

            @Override
            public String getPassword() {
                return userData.getPassword();
            }

            @Override
            public String getUsername() {
                return username;
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
        };
    }

    private UserData getByIdOrNull (String name) {
        String sql = "SELECT name, password, role FROM users WHERE name=:name";
        return jdbcTemplate.queryForObject(
                sql,
                Map.of("name", name),
                new UserDataMapper()
        );
    }

    private class UserDataMapper implements RowMapper<UserData> {
        @Override
        public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
            System.out.println("rs.getString(\"password\") = " + rs.getString("password"));
            System.out.println("rs.getString(\"role\") = " + rs.getString("role"));
            return UserData.builder()
                    .password(rs.getString("password"))
                    .name(rs.getString("role"))
                    .build();
        }
    }

    @Builder
    @Data
    private static class UserData {
        private String name;
        private String password;
        private String role;
    }
}
