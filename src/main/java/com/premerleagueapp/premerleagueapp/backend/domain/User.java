package com.premerleagueapp.premerleagueapp.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private LocalDateTime signUpDate;

    @JsonManagedReference(value = "news-user")
    @OneToMany(targetEntity = News.class,
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<News> news = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return nickname.equals(user.nickname) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, email);
    }
}
