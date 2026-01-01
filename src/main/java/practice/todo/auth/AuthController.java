package practice.todo.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.todo.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request){
    	  Authentication authentication =
                  new UsernamePasswordAuthenticationToken(
                          request.getUserName(),
                          request.getPassword()
                  );

          authenticationManager.authenticate(authentication);

          String token = jwtUtil.generateToken(request.getUserName());
          Map<String, String> response = new HashMap<>();
          response.put("token", token);
          
          return ResponseEntity.ok(response);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody SignupRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }

}
