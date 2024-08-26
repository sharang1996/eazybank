package com.eazybytes.loan.entity;

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
public class Loan extends BaseEntity {
  String mobileNumber;
  String loanNumber;
  String loanType;
  double totalAmount;
  double amountPaid;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long loanId;
}
