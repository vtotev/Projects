package interview.task.themarket.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UserCreateBindingModel {

    @NotBlank
    private String username;

    @NotNull
    @Positive
    private Double account;

    public UserCreateBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserCreateBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Double getAccount() {
        return account;
    }

    public UserCreateBindingModel setAccount(Double account) {
        this.account = account;
        return this;
    }
}
