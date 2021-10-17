package interview.task.themarket.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity {

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    @OneToOne
    private Item item;

    private Double price;

    private Boolean active;

    public Contract() {
    }

    public User getSeller() {
        return seller;
    }

    public Contract setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public Contract setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public Contract setItem(Item item) {
        this.item = item;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Contract setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public Contract setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        if (getBuyer() != null) {
            return String.format("{%n\t\"sellerId\": %d,%n\t\"sellerUsername\": \"%s\"," +
                            "\t\"buyerId\": %d,%n\t\"buyerUsername\": \"%s\"," +
                            "%n\t\"itemId\": %d,%n\t\"price\": %s,%n\t\"active\": %s%n}%n",
                    getSeller().getId(), getSeller().getUsername(), getBuyer().getId(), getBuyer().getUsername(), getItem().getId(), getPrice(), getActive());
        }

        return String.format("{%n\t\"sellerId\": %d,%n\t\"sellerUsername\": \"%s\"," +
                        "%n\t\"itemId\": %d,%n\t\"price\": %s,%n\t\"active\": %s%n}%n",
                getSeller().getId(), getSeller().getUsername(), getItem().getId(), getPrice(), getActive());
    }
}
