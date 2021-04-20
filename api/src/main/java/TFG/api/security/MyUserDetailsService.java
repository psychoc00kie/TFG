package TFG.api.security;

import TFG.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(user_name);

        if ( user == null){
            throw new UsernameNotFoundException("There is no username: "+user_name+" registered");
        }

        return new MyUserDetails(user);
    }
}
