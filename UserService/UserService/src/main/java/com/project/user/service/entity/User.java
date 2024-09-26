package com.project.user.service.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User
{
    @Id
    private String userId;
    private String name;
    private String emailId;
    private String about;
    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
