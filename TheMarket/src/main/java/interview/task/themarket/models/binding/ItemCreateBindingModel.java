package interview.task.themarket.models.binding;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ItemCreateBindingModel {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Long ownerId;

    public ItemCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ItemCreateBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public ItemCreateBindingModel setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
