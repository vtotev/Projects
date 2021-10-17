package interview.task.themarket.web;

import interview.task.themarket.models.binding.UserCreateBindingModel;
import interview.task.themarket.models.entities.User;
import interview.task.themarket.servuce.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    // GET

    @GetMapping({"", "/"})
    public String getUsersHome() {
        var sb = new StringBuilder();
        sb.append("[");
        usersService.getAllUsers().forEach(u -> sb.append(u.toString()));
        sb.append("]");
        return sb.toString();
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id) {
        User userById = usersService.findUserById(id);
        return userById != null ? userById.toString() : "[]";
    }

    // POST

    @PostMapping({"", "/"})
    public ResponseEntity<User> createNewUser(@RequestBody UserCreateBindingModel newUser, UriComponentsBuilder ucBuilder) {
        var user = usersService.createUser(newUser.getUsername(), newUser.getAccount());
        return ResponseEntity.created(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri())
                .build();
    }
}
