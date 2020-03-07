package model;

import lombok.*;
import service.AccountService;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Account implements Model {

    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String city;
    private String gender;
    private AccountService accountService = new AccountService();

    public Account(long id, String userName, String firstName, String lastName, String city, String gender) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.gender = gender;
    }

    @Override
    public List<Model> selectAllItem() {
        return accountService.selectAllItem();
    }

    @Override
    public void deleteItem(long id) {
        accountService.deleteItem(id);
    }

    @Override
    public void updateItem(Model model,long id) {
        accountService.updateItem(model,id);
    }

    @Override
    public void insertItem(Model model) {
        accountService.insertItem(model);
    }

}

