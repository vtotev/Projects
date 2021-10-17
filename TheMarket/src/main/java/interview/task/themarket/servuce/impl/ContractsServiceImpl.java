package interview.task.themarket.servuce.impl;

import interview.task.themarket.models.entities.Contract;
import interview.task.themarket.repository.ContractsRepository;
import interview.task.themarket.servuce.ContractsService;
import interview.task.themarket.servuce.ItemsService;
import interview.task.themarket.servuce.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractsServiceImpl implements ContractsService {

    private final ContractsRepository contractsRepository;
    private final UsersService usersService;
    private final ItemsService itemsService;

    public ContractsServiceImpl(ContractsRepository contractsRepository, UsersService usersService, ItemsService itemsService) {
        this.contractsRepository = contractsRepository;
        this.usersService = usersService;
        this.itemsService = itemsService;
    }

    @Override
    public Contract createContract(Long itemId, Double price) {
        var item = itemsService.findItemById(itemId);
        if (item == null) {
            throw new IllegalStateException(String.format("Item with id: %d don't exist in DB", itemId));
        }

        var contractExists = contractsRepository.findActiveByItemId(itemId);

        if (contractExists.isPresent()) {
            throw new IllegalStateException("There is already active contract for this item");
        }

        var newContract = new Contract();
        newContract.setItem(item).setSeller(item.getOwner()).setPrice(price).setActive(true);
        contractsRepository.save(newContract);

        return contractsRepository.findActiveByItemId(itemId).orElse(null);
    }

    @Override
    public Contract updateContractPrice(Long itemId, Double newPrice) {
        var contract = contractsRepository.findActiveByItemId(itemId).orElse(null);
        if (contract == null || !contract.getActive()) {
            throw new IllegalArgumentException(String.format("No active contract with id: %d in DB", itemId));
        }

        contract.setPrice(newPrice);
        contract = contractsRepository.save(contract);

        return contract;
    }

    @Override
    public List<Contract> getAllActiveContracts() {
        return contractsRepository.findAllActiveContracts();
    }

    @Override
    public Contract closeContract(Long itemId, Long buyerId) {
        var contract = contractsRepository.findActiveByItemId(itemId).orElse(null);
        if (contract == null) {
            throw new IllegalArgumentException(String.format("No active contract for item id: %d in DB", itemId));
        }

        var buyer = usersService.findUserById(buyerId);
        if (buyer == null) {
            throw new IllegalArgumentException(String.format("Buyer with id: %d don't exist in DB", buyerId));
        }

        if (contract.getSeller().getId().equals(buyerId)) {
            throw new IllegalArgumentException("Seller cannot be a buyer");
        }

        if (buyer.getAccount() < contract.getPrice()) {
            throw new IllegalArgumentException("Buyer have insufficient funds");
        }

        contract.setBuyer(buyer).setActive(false);
        contract = contractsRepository.save(contract);
        usersService.decreaseAccountBalance(buyerId, contract.getPrice());
        usersService.increaseAccountBalance(contract.getSeller().getId(), contract.getPrice());

        return contract;
    }

    @Override
    public List<Contract> getAllClosedContracts() {
         return contractsRepository.findAllClosedContracts(null, null, null);
    }

    @Override
    public List<Contract> getAllClosedContractsByItemId(Optional<Long> id) {
         return contractsRepository.findAllClosedContracts(id, null, null);
    }

    @Override
    public List<Contract> getAllClosedContractsBySellerId(Optional<Long> id) {
         return contractsRepository.findAllClosedContracts(null, id, null);
    }

    @Override
    public List<Contract> getAllClosedContractsByBuyerId(Optional<Long> id) {
         return contractsRepository.findAllClosedContracts(null, null, id);
    }

    @Override
    public List<Contract> getAllContractsBySellerId(Long sellerId) {
        return contractsRepository.findAllContractsBySellerId(sellerId);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractsRepository.findAll();
    }
}
