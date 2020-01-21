package pl.cpapp.back.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.cpapp.back.model.User;

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

    public static UserResponse from(User user) {
        return builder().id(user.getId()).name(user.getName()).number(user.getNumber()).surename(user.getSurename()).pseudo(user.getPseudo()).build();
    }
}
