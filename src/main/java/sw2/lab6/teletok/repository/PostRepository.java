package sw2.lab6.teletok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.lab6.teletok.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value="select description from post where id = ?1", nativeQuery = true)
    String findDescripcionbyId(int id);

}
