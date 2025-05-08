package com.example.test_work.model.user;

import com.example.test_work.model.subscription.Subscription;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_subscriptions", joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " subscription_id", referencedColumnName = "id"))
    private Set<Subscription> subscriptions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
