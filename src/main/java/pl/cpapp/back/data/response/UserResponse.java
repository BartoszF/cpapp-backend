package pl.cpapp.back.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.cpapp.back.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String surename;
    private String pseudo;
    private String number;
    private String role;
    private List<UserResponse> contacts;

    public static UserResponse from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .number(user.getNumber())
                .surename(user.getSurename())
                .pseudo(user.getPseudo())
                .role(user.getRole().getRole())
                .contacts(user.getContacts().stream().map(u -> UserResponse.from(u)).collect(Collectors.toList()))
                .build();
    }
}
