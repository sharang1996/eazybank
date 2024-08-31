create TABLE IF NOT EXISTS `card` (
  `card_id` int AUTO_INCREMENT,
  `mobile_number` varchar(10) NOT NULL,
  `card_number` varchar(16) NOT NULL,
  `card_type` varchar(20) NOT NULL,
  `total_limit` DOUBLE NOT NULL,
  `amount_used` DOUBLE NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`card_id`)
);
