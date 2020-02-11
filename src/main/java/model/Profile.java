package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Profile implements Model {

    private int id;
    private String userName;
    private String jobTitle;
    private String department;
    private String company;
    private String skill;
}