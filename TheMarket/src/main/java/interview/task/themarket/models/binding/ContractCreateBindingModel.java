package interview.task.themarket.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ContractCreateBindingModel {
    @NotNull
    @Positive
    private Long itemId;

    @NotNull
    @Positive
    private Double price;

    public ContractCreateBindingModel() {
    }

    public Long getItemId() {
        return itemId;
    }

    public ContractCreateBindingModel setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ContractCreateBindingModel setPrice(Double price) {
        this.price = price;
        return this;
    }
}
