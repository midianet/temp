package midianet.cond.helper;

import io.jsonwebtoken.*;
import midianet.cond.domain.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtHelper {
    
    @Value("${cookie}")
    private String token;
    
    @Value("${key}")
    private String key;

    public String generateToken(Auth auth) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Claims c = Jwts.claims()
        .setSubject(auth.getUser());
        c.put("name",auth.getName());
        c.put("email","teste@teste.com");
        c.put("cpf","85402419191");
        //c.put("data", new Date());
        //c.put("client",agent.getClient());
        //c.put("browser", agent.getBrowser());
        //c.put("so", agent.getSo());
        //c.put("ip", agent.getIp());
        c.put("authorities",auth.getAuthorities().toArray());
        JwtBuilder builder = Jwts.builder()
            .setIssuedAt(now)
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setClaims(c)
            .signWith(SignatureAlgorithm.HS512, key);
        return builder.compact();
    }

    public void validate(String token){
        Optional.ofNullable(token).ifPresent(s ->{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        });
    }
    
}