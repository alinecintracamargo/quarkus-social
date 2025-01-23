package io.github.alinecintracamargo.quarkussocial.domain.repository;

import io.github.alinecintracamargo.quarkussocial.domain.model.Follower;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.bytebuddy.description.annotation.AnnotationDescription;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {
}
