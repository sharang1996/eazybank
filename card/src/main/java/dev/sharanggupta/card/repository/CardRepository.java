package dev.sharanggupta.card.repository;

import dev.sharanggupta.card.entity.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
  Optional<Card> findByMobileNumber(String mobileNumber);
}
