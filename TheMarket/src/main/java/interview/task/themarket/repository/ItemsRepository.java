package interview.task.themarket.repository;

import interview.task.themarket.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.owner.id = :owner_id")
    List<Item> findAllItemsWithOwnerId(@Param("owner_id") Long ownerId);

}
