package com.nhom10.webts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;


    @JsonIgnore
    private String type = "Bearer";
    private Long id;
    private String username;
    private String sdt;
    //@JsonIgnore
    private String role;

    public JwtResponse() {
    }

    public JwtResponse(String token, String type, Long id, String username, String sdt, String role) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.sdt = sdt;
        this.role = role;
    }

    public JwtResponse(String token, Long id, String username, String sdt, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.sdt = sdt;
        this.role = role;
    }
}
