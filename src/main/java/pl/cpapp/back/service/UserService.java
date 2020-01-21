package pl.cpapp.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cpapp.back.model.Role;
import pl.cpapp.back.model.User;
import pl.cpapp.back.repository.RoleRepository;
import pl.cpapp.back.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User saveUser(User user) throws Exception {

        String pass = user.getPin();
        user.setPin(passwordEncoder.encode(pass));

        User createdUser = userRepository.save(user);

        Role role = new Role();
        role.setUser(createdUser);
        role.setRole("USER");

        roleRepository.save(role);

        return userRepository.getOne(user.getId());
    }
}
