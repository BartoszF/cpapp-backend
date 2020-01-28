package pl.cpapp.back.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserRequest {
    private String phoneNumber;
    private String pin;
    private String name;
    private String surename;
    private String pseudo;
    private String description;
}
