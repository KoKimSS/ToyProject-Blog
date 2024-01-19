package ToyProject.blogWorld;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class BlogWorldApplication {
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.of(UUID.randomUUID().toString());
	}
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {

		return new JPAQueryFactory(entityManager);
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogWorldApplication.class, args);
	}

}
