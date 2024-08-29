package dev.sharanggupta.card.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {
  String mobileNumber;
  String cardNumber;
  String cardType;
  double totalLimit;
  double amountUsed;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;
}
