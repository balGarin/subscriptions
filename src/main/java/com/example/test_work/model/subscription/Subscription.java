package com.example.test_work.model.subscription;

import com.example.test_work.model.user.User;
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
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_subscriptions", joinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = " user_id", referencedColumnName = "id"))
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
