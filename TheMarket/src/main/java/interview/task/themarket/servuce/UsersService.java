package interview.task.themarket.servuce;

import interview.task.themarket.models.entities.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UsersService {

    User createUser(String username, Double account);

    User findUserById(Long ownerId);

    Boolean increaseAccountBalance(Long userId, Double value);

    Boolean decreaseAccountBalance(Long userId, Double value);

    List<User> getAllUsers();
}
