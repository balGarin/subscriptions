package com.example.test_work.repository.subscription;

import com.example.test_work.dto.subscription.SubscriptionTopDto;
import com.example.test_work.model.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    Optional<Subscription> findByTitle(String title);

    @Query(value = """
            SELECT  s.title, count(distinct us.user_id) as rate
            	FROM public.subscriptions as s
            	JOIN users_subscriptions as us ON us.subscription_id=s.id
            	GROUP BY s.title
            	ORDER BY rate DESC
            	LIMIT ?1""", nativeQuery = true)
    List<SubscriptionTopDto> getTop(Integer limit);

}
