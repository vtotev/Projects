package interview.task.themarket.web;

import interview.task.themarket.models.binding.ContractCreateBindingModel;
import interview.task.themarket.models.entities.Contract;
import interview.task.themarket.servuce.ContractsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractsService contractsService;

    public ContractController(ContractsService contractsService) {
        this.contractsService = contractsService;
    }


    @GetMapping({"", "/"})
    public String ContractsHome() {
        var sb = new StringBuilder();
        sb.append("[");
        contractsService.getAllContracts().forEach(c -> sb.append(c.toString()));
        sb.append("]");
        return sb.toString();
    }

    // CREATE CONTRACT

    @PostMapping({"", "/"})
    public ResponseEntity<Contract> createContract(@RequestBody ContractCreateBindingModel contract, UriComponentsBuilder ucBuilder) {
        var newContract = contractsService.createContract(contract.getItemId(), contract.getPrice());
        return ResponseEntity.created(ucBuilder.path("/contracts/{id}").buildAndExpand(newContract.getId()).toUri()).build();
    }

    // UPDATE CONTRACT PRICE

    @PostMapping("/update")
    public ResponseEntity<Contract> updatePrice(@RequestBody ContractCreateBindingModel contract, UriComponentsBuilder ucBuilder) {
        var updatedContract = contractsService.updateContractPrice(contract.getItemId(), contract.getPrice());
        return ResponseEntity.created(ucBuilder.path("/contracts/{id}").buildAndExpand(updatedContract.getId()).toUri()).build();
    }


    // CLOSED CONTRACTS
    @GetMapping("/closed/all")
    public String getAllClosedContracts() {
        return contractsService.getAllClosedContracts().toString();
    }

    @GetMapping("/closed/item/{id}")
    public String getAllClosedContractsByItemId(@PathVariable("id") Optional<Long> id) {
        return contractsService.getAllClosedContractsByItemId(id).toString();
    }

    @GetMapping("/closed/seller/{id}")
    public String getAllClosedContractsBySellerId(@PathVariable("id") Optional<Long> id) {
        return contractsService.getAllClosedContractsBySellerId(id).toString();
    }

    @GetMapping("/closed/buyer/{id}")
    public String getAllClosedContractsByBuyerId(@PathVariable("id") Optional<Long> id) {
        return contractsService.getAllClosedContractsByBuyerId(id).toString();
    }

    // ACTIVE CONTRACTS
    @GetMapping("/active")
    public String getAllActiveContracts() {
        return contractsService.getAllActiveContracts().toString();
    }

}
