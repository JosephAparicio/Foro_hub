package com.alura.ForoHubAPI.domain.model;

import java.util.List;

import com.alura.ForoHubAPI.dto.user.RegisterUserDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Profile")
@Table(name = "profiles")
@Data
@EqualsAndHashCode(of = "profileId")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String name;
    @ManyToMany(mappedBy = "profiles")
    private List<User> users;

    public Profile() {
    }

    public Profile(Long profileId, String name, List<User> users) {
        this.profileId = profileId;
        this.name = name;
        this.users = users;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Profile(RegisterUserDTO data){
        this.name = data.profileType().toString();
    }
}
