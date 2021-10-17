package interview.task.themarket.servuce;

import interview.task.themarket.models.entities.Item;

import java.util.List;

public interface ItemsService {

    Item createItem(String itemName, Long ownerId);

    List<Item> getAllItemsWithOwnerId(Long ownerId);

    Item findItemById(Long itemId);

    List<Item> getAllItems();
}
