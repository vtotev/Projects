package interview.task.themarket.models.binding;

public class ContractCreateBindingModel {
    private Long itemId;
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
