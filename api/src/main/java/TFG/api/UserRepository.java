package TFG.api;

import TFG.api.security.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM user u WHERE u.user_name = :user_name")
    public User getUserByUsername(@Param("user_name") String user_name);
}
