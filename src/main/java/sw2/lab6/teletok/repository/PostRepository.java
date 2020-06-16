package sw2.lab6.teletok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.lab6.teletok.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

        @Query(value="SELECT * FROM post where description =?1 or user_id = (select user_id from user where username = ?1)",nativeQuery = true)
    List<Post> buscarPorDescripOUser(String search);
}
