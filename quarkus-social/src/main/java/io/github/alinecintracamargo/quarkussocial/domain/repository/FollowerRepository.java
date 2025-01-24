package io.github.alinecintracamargo.quarkussocial.domain.repository;

import io.github.alinecintracamargo.quarkussocial.domain.model.Follower;
import io.github.alinecintracamargo.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import net.bytebuddy.description.annotation.AnnotationDescription;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;


@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    public boolean follows(User follower, User user) {

        var params = Parameters.with("follower", follower)
                .and("user", user);

        PanacheQuery<Follower> query = find("followers = :follower and user = :user", params);
        Optional<Follower> result = query.firstResultOptional();

        return result.isPresent();
    }

    public List<Follower> findByUser(Long userId){
        PanacheQuery<Follower> query = find("user.id", userId);
        return query.list();
    }
}
