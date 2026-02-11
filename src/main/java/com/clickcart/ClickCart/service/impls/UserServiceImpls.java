package com.clickcart.ClickCart.service.impls;

import com.clickcart.ClickCart.dto.request.UserLoginRequestDto;
import com.clickcart.ClickCart.dto.request.UserRequestDTO;
import com.clickcart.ClickCart.dto.response.UserLoginResponse;
import com.clickcart.ClickCart.dto.response.UserResponseDTO;
import com.clickcart.ClickCart.exception.UserAlreadyExistsException;
import com.clickcart.ClickCart.model.User;
import com.clickcart.ClickCart.repository.UserRepository;
import com.clickcart.ClickCart.service.UserService;
import com.clickcart.ClickCart.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpls implements UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {

        User user = UserTransformer.userRequestDtoToUser(userRequestDTO);
        user.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
            try {
                User savedUser = userRepo.save(user);
                return UserTransformer.userToUserResponseDto(savedUser);

            } catch (DataIntegrityViolationException ex) {
                throw new UserAlreadyExistsException("Email / Mobile / Username already exists");
            }
       }


}

