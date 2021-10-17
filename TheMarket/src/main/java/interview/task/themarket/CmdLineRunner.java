package interview.task.themarket;

import interview.task.themarket.models.entities.Contract;
import interview.task.themarket.servuce.ContractsService;
import interview.task.themarket.servuce.ItemsService;
import interview.task.themarket.servuce.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class CmdLineRunner implements CommandLineRunner {
    private final UsersService usersService;
    private final ItemsService itemsService;
    private final ContractsService contractsService;
    private final Scanner scan = new Scanner(System.in);

    public CmdLineRunner(UsersService usersService, ItemsService itemsService, ContractsService contractsService) {
        this.usersService = usersService;
        this.itemsService = itemsService;
        this.contractsService = contractsService;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] values = {"Commands: ", "1. Create User", "2. Add Item", "3. Get All Items",
                "4. Create contract", "5. Update contract price", "6. Get All Active Contracts",
                "7. Close contract", "8. Get All Closed Contracts", "9. Print All Contracts With Seller Id",
                "-----------------------------------",
                "Enter command number to proceed: "};
        Arrays.stream(values).forEach(System.out::println);
        String cmd = "";
        while (!("exit").equals(cmd = scan.nextLine())) {
            int cmdNum = Integer.parseInt(cmd);
            switch (cmdNum) {
                case 1 -> createUser();
                case 2 -> createItem();
                case 3 -> printAllItemsWithOwnerId();
                case 4 -> createContract();
                case 5 -> updateContractPrice();
                case 6 -> printActiveContracts();
                case 7 -> closeContract();
                case 8 -> printClosedContracts();
                case 9 -> printAllContractsWithSellerId();
            }
            System.out.println("");
            System.out.println("Enter command number to proceed: ");
        }
    }

    private void printAllContractsWithSellerId() {
        System.out.print("Enter seller id: ");
        Long sellerId = Long.parseLong(scan.nextLine());
        var allSellerContracts = contractsService.getAllContractsBySellerId(sellerId);
        if (allSellerContracts.isEmpty()) {
            System.out.println("There are no contracts for this seller.");
            return;
        }
        allSellerContracts.forEach(System.out::println);
    }

    private void closeContract() {
        System.out.print("Enter contract item id: ");
        Long itemId = Long.valueOf(scan.nextLine());
        System.out.print("Enter buyer ud: ");
        Long buyerId = Long.valueOf(scan.nextLine());

        contractsService.closeContract(itemId, buyerId);

        System.out.println("Contract Closed");
    }

    private void printClosedContracts() {
        var allClosedContracts = contractsService.getAllClosedContracts();
        if (allClosedContracts.isEmpty()) {
            System.out.println("There are no closed contracts.");
            return;
        }
        allClosedContracts.forEach(System.out::println);
    }

    private void printActiveContracts() {
        var allActiveContracts = contractsService.getAllActiveContracts();
        if (allActiveContracts.isEmpty()) {
            System.out.println("There are no active contracts.");
            return;
        }
        allActiveContracts.forEach(System.out::println);
    }

    private void updateContractPrice() {
        System.out.print("Enter item id: ");
        Long itemId = Long.valueOf(scan.nextLine());
        System.out.print("Enter new price: ");
        Double newPrice = Double.parseDouble(scan.nextLine());

        var result = contractsService.updateContractPrice(itemId, newPrice);

        System.out.println(String.format("Price for contract on item with id: %d successfully updated.", itemId));
    }

    private void createContract() {
        System.out.print("Enter item id: ");
        Long itemid = Long.valueOf(scan.nextLine());
        System.out.print("Enter price: ");
        Double price = Double.parseDouble(scan.nextLine());

        var newContract = contractsService.createContract(itemid, price);
        String result = String.format("New contract for item \"%s\" successfully created.", newContract.getItem().getName());;

        System.out.println(result);
    }

    private void printAllItemsWithOwnerId() {
        System.out.print("Enter owner id: ");
        Long ownerId = Long.parseLong(scan.nextLine());
        itemsService
                .getAllItemsWithOwnerId(ownerId)
                .forEach(System.out::println);
    }

    private void createItem() {
        System.out.print("Enter item name: ");
        String itemName = scan.nextLine();
        System.out.print("Enter owner id: ");
        Long ownerId = Long.valueOf(scan.nextLine());
        var item = itemsService.createItem(itemName, ownerId);
        String result = String.format("Successfully added Item with name: %s and owner: %s", item.getName(), item.getOwner().getUsername());
        System.out.println(result);
    }

    private void createUser() {
        System.out.print("Enter user name: ");
        String userName = scan.nextLine();
        System.out.print("Enter account balance: ");
        Double account = Double.parseDouble(scan.nextLine());
        var user = usersService.createUser(userName, account);
        System.out.printf("Successful added new user with username: %s and account %s%n", userName, account);
    }
}
