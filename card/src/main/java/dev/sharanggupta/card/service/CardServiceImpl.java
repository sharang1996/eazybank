package dev.sharanggupta.card.service;

import dev.sharanggupta.card.dto.CardDto;
import dev.sharanggupta.card.entity.Card;
import dev.sharanggupta.card.exception.CardAlreadyExistsException;
import dev.sharanggupta.card.exception.InternalServerException;
import dev.sharanggupta.card.exception.ResourceNotFoundException;
import dev.sharanggupta.card.mapper.CardMapper;
import dev.sharanggupta.card.repository.CardRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {
  private final CardRepository cardRepository;

  @Override
  public void createCard(String mobileNumber) {
    Optional<Card> existingCard = cardRepository.findByMobileNumber(mobileNumber);
    if (existingCard.isPresent()) {
      throw new CardAlreadyExistsException(
          "Card already registered with given mobileNumber " + mobileNumber);
    }
    cardRepository.save(createNewCard(mobileNumber));
  }

  @Override
  public CardDto fetchCard(String mobileNumber) {
    Card card =
        cardRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
    return CardMapper.mapToCardDto(card, new CardDto());
  }

  @Override
  public void updateCard(CardDto cardDto) {
    String mobileNumber = cardDto.getMobileNumber();
    Card currentCard =
        cardRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Card", "MobileNumber", mobileNumber));
    Card updatedCard = CardMapper.mapToCard(cardDto, new Card());
    updatedCard.setCardId(currentCard.getCardId());
    cardRepository.save(updatedCard);
  }

  @Override
  public void deleteCard(String mobileNumber) {
    Card card =
        cardRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
    try {
      cardRepository.deleteById(card.getCardId());
    } catch (DataAccessException exception) {
      throw new InternalServerException(exception.getCause(), exception.getMessage());
    }
  }

  private Card createNewCard(String mobileNumber) {
    Card newCard = new Card();
    long randomCardNumber = (long) (Math.random() * 10000000000000000L);
    newCard.setCardNumber(Long.toString(randomCardNumber));
    newCard.setMobileNumber(mobileNumber);
    newCard.setCardType("Credit Card");
    newCard.setTotalLimit(1_00_000);
    newCard.setAmountUsed(0);
    return newCard;
  }
}
