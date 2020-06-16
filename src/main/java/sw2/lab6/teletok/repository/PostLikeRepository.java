package sw2.lab6.teletok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.lab6.teletok.entity.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    @Query(value="select count(post_id) from post_like where post_id=?1", nativeQuery = true)
    Integer cantidaddelikes(int id);
}
