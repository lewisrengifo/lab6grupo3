package sw2.lab6.teletok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.lab6.teletok.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    @Query(value="select u.* from user u inner join post p on u.id = p.user_id where p.user_id=?1", nativeQuery = true)
    User findAutorPost(int id);
}
