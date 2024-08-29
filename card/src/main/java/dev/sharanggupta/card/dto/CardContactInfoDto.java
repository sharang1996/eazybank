package dev.sharanggupta.card.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "card")
public record CardContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {}
