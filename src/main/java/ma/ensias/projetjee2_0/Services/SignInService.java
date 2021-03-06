package ma.ensias.projetjee2_0.Services;

import ma.ensias.projetjee2_0.Responses.SignInResponse;
import ma.ensias.projetjee2_0.entites.User;
import ma.ensias.projetjee2_0.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SignInService {
    @Autowired
    UserRepository userRepository;

    public static final String USER_SESSION = "userSession";

    public SignInResponse signIn(String username, String password, HttpSession session)
    {
        User user = userRepository.findByUsernameEquals(username);
        session.setAttribute(USER_SESSION,user);
        if(user == null)
        {
            return new SignInResponse(false,"Nonexistent username");
        }
        else if(!user.getPassword().equals(SignUpService.getMd5(password)))
        {
            return new SignInResponse(false,"Wrong password");
        }
        else
        {
            return new SignInResponse(true,user.getId());
        }
    }
}
