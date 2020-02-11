package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Account implements Model {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String city;
    private String gender;
}