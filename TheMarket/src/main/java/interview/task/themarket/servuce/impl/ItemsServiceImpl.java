package interview.task.themarket.servuce.impl;

import interview.task.themarket.models.entities.Item;
import interview.task.themarket.models.entities.User;
import interview.task.themarket.repository.ItemsRepository;
import interview.task.themarket.servuce.ItemsService;
import interview.task.themarket.servuce.UsersService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsServiceImpl implements ItemsService {

    private final ItemsRepository itemsRepository;
    private final UsersService usersService;

    public ItemsServiceImpl(ItemsRepository itemsRepository, UsersService usersService) {
        this.itemsRepository = itemsRepository;
        this.usersService = usersService;
    }

    @Override
    public Item createItem(String itemName, Long ownerId) {
        var owner = usersService.findUserById(ownerId);

        if (owner == null) {
            throw new IllegalArgumentException("User not found");
        }

        var newItem = new Item();
        newItem.setName(itemName).setOwner(owner);

        itemsRepository.save(newItem);

        return itemsRepository.findById(newItem.getId()).orElse(null);
    }

    @Override
    public List<Item> getAllItemsWithOwnerId(Long ownerId) {
        return itemsRepository.findAllItemsWithOwnerId(ownerId);
    }

    @Override
    public Item findItemById(Long itemId) {
        return itemsRepository.findById(itemId).orElse(null);
    }

    @Override
    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }
}
