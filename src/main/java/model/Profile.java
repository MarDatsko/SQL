package model;

import lombok.*;
import service.ProfileService;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Profile implements Model {

    private long id;
    private String userName;
    private String jobTitle;
    private String department;
    private String company;
    private String skill;

    private ProfileService profileService = new ProfileService();

    public Profile(long id, String userName, String jobTitle, String department, String company, String skill) {
        this.id = id;
        this.userName = userName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.company = company;
        this.skill = skill;
    }

    @Override
    public List<Model> selectAllItem() {
        return profileService.selectAllItem();
    }

    @Override
    public void deleteItem(long id) {
        profileService.deleteItem(id);
    }

    @Override
    public void updateItem(Model model, long id) {
        profileService.updateItem(model, id);
    }

    @Override
    public void insertItem(Model model) {
        profileService.insertItem(model);
    }
}