package pl.cpapp.back.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String number;
    private String pin;
}
