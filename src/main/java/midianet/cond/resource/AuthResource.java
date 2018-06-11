package midianet.cond.resource;

import midianet.cond.domain.Auth;
import midianet.cond.helper.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/auth",headers = {})
public class AuthResource {
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@PostMapping(path = "/login", produces = "text/plain")//MediaType.TEXT_PLAIN_VALUE)
	@ResponseStatus(HttpStatus.OK)
	
	public String auth(@RequestParam("user") String user, @RequestParam("password") String password ){
		Assert.isTrue("123".equals(password), "Usuário e ou senha inválida");
		Auth auth = Auth.builder().user(user).name("Usuário " + user ).authorities(new ArrayList<>()).build();
		auth.getAuthorities().add("ROLE_USER");
		return jwtHelper.generateToken(auth);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String works() {
		return "it`s Works";
	}
	
}
