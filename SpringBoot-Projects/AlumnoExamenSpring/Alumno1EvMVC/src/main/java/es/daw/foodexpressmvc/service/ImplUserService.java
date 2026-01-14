package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.UserRegisterDTO;
import es.daw.foodexpressmvc.entity.Role;
import es.daw.foodexpressmvc.entity.User;
import es.daw.foodexpressmvc.exception.PasswordsDoNotMatchException;
import es.daw.foodexpressmvc.exception.UsernameAlreadyExistsException;
import es.daw.foodexpressmvc.repository.RoleRepository;
import es.daw.foodexpressmvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegisterDTO dto) {

        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new PasswordsDoNotMatchException("Las contraseñas no coinciden");
        }

        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow( () -> new RuntimeException("Rol ROLE_USER no encontrado"));

        user.addRole(roleUser);

        userRepository.save(user);

    }
}
