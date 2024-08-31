create TABLE IF NOT EXISTS `loan` (
  `loan_id` int AUTO_INCREMENT,
  `mobile_number` varchar(10) NOT NULL,
  `loan_number` varchar(16) NOT NULL,
  `loan_type` varchar(20) NOT NULL,
  `total_amount` DOUBLE NOT NULL,
  `amount_paid` DOUBLE NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`loan_id`)
);
