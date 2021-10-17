package interview.task.themarket.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private Double account;

    @OneToMany(mappedBy = "owner")
    private Set<Item> items;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public Double getAccount() {
        return account;
    }

    public User setAccount(Double account) {
        this.account = account;
        return this;
    }

    public Set<Item> getItems() {
        return items;
    }

    public User setItems(Set<Item> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return String.format("{%n\t\"id\": %d,%n\t\"username\": \"%s\",%n\t\"account\": %s%n}%n",
                getId(), getUsername(), getAccount());
    }
}
