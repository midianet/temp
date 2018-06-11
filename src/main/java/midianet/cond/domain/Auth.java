package midianet.cond.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Auth {
	private String user;
	private String name;
	private String token;
	private List<String> authorities;
}
