Feature: Logowanie do aplikacji i dodanie adresu
  Scenario Outline:Logowanie do aplikacji za pomocą poprawnych danych oraz dodanie adresu dostawy oraz weryfikacja poprawności
    Given Uzytkownik znajduje sie na stronie glownej aplikacji
    When Uzytkownik wybiera opcje SignIn
    Then Uzytkownik wpisuje poprawny adres email oraz haslo a nastepnie zatwierdza dane logowania
    And Uzytkownik klika w kafelek Addresses a nastepnie klika w Create new address i zostaje przeniesiony do formularza z miejscami do wpisania danych
    Then Uzytkownik uzupelnia pola "<alias>", "<address>", "<city>", "<zipCode>", "<phone>" danymi oraz zatwierdza przyciskiem save
Examples:
  |alias| address       | city  | zipCode|  phone        |
  |Home | 38 Knocklyon  | London| 80-200 |  0044519834576|