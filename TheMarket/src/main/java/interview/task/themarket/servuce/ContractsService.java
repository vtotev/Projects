package interview.task.themarket.servuce;

import interview.task.themarket.models.entities.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractsService {

    Contract createContract(Long itemId, Double price);
    Contract updateContractPrice(Long itemId, Double newPrice);
    List<Contract> getAllActiveContracts();
    Contract closeContract(Long itemId, Long buyerId);
    List<Contract> getAllClosedContracts();
    List<Contract> getAllClosedContractsByItemId(Optional<Long> id);
    List<Contract> getAllClosedContractsBySellerId(Optional<Long> id);
    List<Contract> getAllClosedContractsByBuyerId(Optional<Long> id);
    List<Contract> getAllContractsBySellerId(Long sellerId);
    List<Contract> getAllContracts();
}
