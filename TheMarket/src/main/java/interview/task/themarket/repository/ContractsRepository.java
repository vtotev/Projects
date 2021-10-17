package interview.task.themarket.repository;

import interview.task.themarket.models.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractsRepository extends JpaRepository<Contract, Long> {

    @Query(value = "select * from contracts c where c.active = true", nativeQuery = true)
    List<Contract> findAllActiveContracts();

//    @Query(value = "select * from contracts c where c.active = false", nativeQuery = true)
//    List<Contract> findAllClosedContracts();

    @Query(value = "select * from contracts c where c.active = false and " +
            "(:itemId IS NULL or c.item_id = :itemId) and " +
            "(:sellerId IS NULL or c.seller_id = :sellerId) and " +
            "(:buyerId IS NULL or c.buyer_id = :buyerId)", nativeQuery = true)
    List<Contract> findAllClosedContracts(@Param("itemId") Optional<Long> itemId,
                                          @Param("sellerId") Optional<Long> sellerId,
                                          @Param("buyerId") Optional<Long> buyerId);

    @Query(value = "select * from contracts c where c.item_id = :item_id and c.active = true ", nativeQuery = true)
    Optional<Contract> findActiveByItemId(@Param("item_id") Long itemId);

    @Query("select c from Contract c where c.seller.id = :id")
    List<Contract> findAllContractsBySellerId(@Param("id") Long sellerId);
}
