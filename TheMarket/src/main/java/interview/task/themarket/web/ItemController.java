package interview.task.themarket.web;

import interview.task.themarket.models.binding.ItemCreateBindingModel;
import interview.task.themarket.models.entities.Item;
import interview.task.themarket.servuce.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemsService itemsService;

    public ItemController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    // GET

    @GetMapping({"", "/"})
    public String itemsHome() {
        var sb = new StringBuilder();
        sb.append("[");
        itemsService.getAllItems().forEach(i -> sb.append(i.toString()));
        sb.append("]");
        return sb.toString();
    }

    @GetMapping("/{id}")
    public String getItemWithId(@PathVariable("id") Long id) {
        Item itemById = itemsService.findItemById(id);
        return itemById != null ? itemById.toString() : "[]";
    }

    @GetMapping("/owner/{id}")
    public String getAllItemsWithOwnerId(@PathVariable("id") Long id) {
        List<Item> itemsList = itemsService.getAllItemsWithOwnerId(id);
        return itemsList.toString();
    }

    // POST

    @PostMapping("/")
    public ResponseEntity<Item> createNewItem(@RequestBody ItemCreateBindingModel newItem, UriComponentsBuilder ucBuilder) {
        var item = itemsService.createItem(newItem.getName(), newItem.getOwnerId());
        return ResponseEntity.created(ucBuilder.path("/items/{id}").buildAndExpand(item.getId()).toUri()).build();
    }
}