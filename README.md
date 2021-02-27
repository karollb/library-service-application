# Aplikacja do zarządzania biblioteką.

## FUNKCJONALNOŚCI:
- Administrator może dodawać nowe książki do bazy danych.
- Administrator może dodawać nowych pracowników.
- Pracownik może wypożyczyć książki klientowi ( wybranego z bazy danych lub wprowadzić nowe dane klienta).
- Dane klienta który już coś wypożyczył są zapisywane w bazie danych i mogą zostać ponownie użyte, bez ich wprowadzania.
- Pracownik może przyjąć zwrot książki (książka przechodzi do stanu "Dostępnego" i może zostać wyporzyczona kolejnej osobie).

## DZIAŁANIE PROGRAMU:
### Wypożyczenie książki: 
 Pracownik wybiera książki z listy znajdującej się na stronie głównej, a następnie naciska przycisk "wypożycz", gdzie zostanie przekierowany na stronę z wybranymi pozycjami.
 Potem po naciśnięciu przycisku "Wybierz klienta" może wybrać klienta który jest zapisany w bazie danych, lub wybrać opcje "nowy klient".
 Po wpisaniu danych nowego klienta wypożyczenie i nowy klient zostaną automatycznie dodane do bazy danych, a status książki zmieni się na "Niedostępny".
 
 ### Zwrot książki:
 Aby przyjąć zwrot książki pracownik musi przejść do strony "Wypożyczenia", gdzie znajdują się dane klientów posiadających wypżyczone książki.
 Po wybraniu odpowiedniego klienta z listy pracownik zostanie przekierowany do strony, gdzie znajdują się wypożyczone przez danego klienta pozycje.
 Tam może wybrać książke zwracaną przez klienta. Stan książki zostanie automatycznie zmieniony na "Dostępny" i będzie ją można ponownie wypożyczyć.
 
 ## WEB SERVICE:
 http://localhost:8080/completeTheDatabase
 Powyższy link automatycznie stworzy konto administratora (login: admin, pass: admin), 
 i konto użytkownika (login: karol, pass: karol),
 oraz automatycznie doda do bazy danych 5 książek.
 
 
 
 
