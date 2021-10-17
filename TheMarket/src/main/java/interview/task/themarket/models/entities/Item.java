package interview.task.themarket.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User owner;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Item setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public String toString() {
        return String.format("{%n\t\"Id\": %d,%n\t\"name\": \"%s\",%n\t\"ownerId\": %d,%n\t\"ownerUsername\": \"%s\"%n}%n",
                getId(), getName(), owner.getId(), owner.getUsername());
    }
}
