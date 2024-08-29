package dev.sharanggupta.card.mapper;

import dev.sharanggupta.card.dto.CardDto;
import dev.sharanggupta.card.entity.Card;

public class CardMapper {
  public static CardDto mapToCardDto(Card card, CardDto cardDto) {
    cardDto.setCardNumber(card.getCardNumber());
    cardDto.setCardType(card.getCardType());
    cardDto.setMobileNumber(card.getMobileNumber());
    cardDto.setTotalLimit(card.getTotalLimit());
    cardDto.setAmountUsed(card.getAmountUsed());
    return cardDto;
  }

  public static Card mapToCard(CardDto cardDto, Card card) {
    card.setCardNumber(cardDto.getCardNumber());
    card.setCardType(cardDto.getCardType());
    card.setMobileNumber(cardDto.getMobileNumber());
    card.setTotalLimit(cardDto.getTotalLimit());
    card.setAmountUsed(cardDto.getAmountUsed());
    return card;
  }
}
