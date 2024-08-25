package com.eazybytes.card.service;

import com.eazybytes.card.dto.CardDto;

public interface CardService {

  void createCard(String mobileNumber);

  CardDto fetchCard(String mobileNumber);

  void updateCard(CardDto cardDto);

  void deleteCard(String mobileNumber);
}
