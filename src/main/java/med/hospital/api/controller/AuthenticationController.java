package med.hospital.api.controller;

import jakarta.validation.Valid;
import med.hospital.api.domain.usersapi.DataAuthenticateUser;
import med.hospital.api.domain.usersapi.UserAPI;
import med.hospital.api.infra.security.DataJWTToken;
import med.hospital.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid DataAuthenticateUser dataAuthenticateUser) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                dataAuthenticateUser.login(), dataAuthenticateUser.keyUser());
        var authenticatedUserAPI = authenticationManager.authenticate(authenticationToken);
        var JWTToken = tokenService.generateToken((UserAPI) authenticatedUserAPI.getPrincipal()); //casteamos a objeto UserAPI
        System.out.println(JWTToken);
        // el Principal es el objeto/instancia de usuarioAPI que ya fue autenticado en el sistema
        return ResponseEntity.ok(new DataJWTToken(JWTToken));
    }
}
