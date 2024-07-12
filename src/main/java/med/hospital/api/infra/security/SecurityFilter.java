package med.hospital.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.hospital.api.domain.usersapi.IUserAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUserAPIRepository userAPIRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        var authHeather = request.getHeader("Authorization");
        if(authHeather != null){
            var authToken = authHeather.replace("Bearer ", "");
            var userAPIname = tokenService.getSubject(authToken); //Extraemos el subject/username
            if(userAPIname != null) {
                //Token valido
                var userAPI = userAPIRepository.findByLogin(userAPIname);
                //Forzamos a Spring a un inicio de sesión, le decimos este login es válido
                // para mí porque existe en la base de datos, autenticamos al usuario:
                var authentication = new UsernamePasswordAuthenticationToken(userAPI, null,
                        userAPI.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcmlhbmEubWFkaXgiLCJpc3MiOiJob3NwaXRhbCBtZWQiLCJpZCI6MywiZXhwIjoxNzIwNzc5MTYzfQ.nIYf01LZqyjEr980-Ku9MumTZ7BcVRjsnrRb3TB4Vik

    }
}
//      El nombre del Heather específico donde llega el token por estándar es "Authorization"
//      Por defecto llega con el prefijo "Bearer", lo queremos vacío
//      System.out.println("Filter esta siendo llamado!");
//      Obtener el token
