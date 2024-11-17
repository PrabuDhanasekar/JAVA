# Product Price Scraper and Comparator

This Java project scrapes product details from **Amazon** and **Flipkart**, stores them in a MySQL database, and compares the prices to find the lowest one. The application allows users to search for products, view the lowest price, and manage the database contents.

---

## Features

- **Web Scraping**:
  - Fetch product names and prices from Amazon and Flipkart search pages.
- **Price Comparison**:
  - Automatically determine which platform offers the lowest price for a product.
- **Database Integration**:
  - Save product details to a MySQL database for future analysis.
  - Clear database tables as needed.
- **Interactive CLI**:
  - User-friendly input prompts to search products and manage data.

---

## Requirements

### Software

- **Java Development Kit (JDK)**: Version 8 or above
- **Apache Maven**: For managing dependencies
- **MySQL**: For storing scraped product data
- **Jsoup**: HTML parser library for web scraping

### Dependencies

The project uses the following libraries:

- [**Jsoup**](https://jsoup.org/): For HTML parsing
- **MySQL Connector/J**: JDBC driver for MySQL

---
