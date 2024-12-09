# 🎮 Hangman Game - JavaFX Desktop Application

**Hangman Game** to aplikacja desktopowa stworzona z użyciem **JavaFX** oraz **Maven**, która umożliwia rozgrywkę w klasyczną grę w zgadywanie słów.

##  Zawartość projektu

1. **Kontrolery** – obsługują logikę gry i interakcje użytkownika.
2. **Widoki** – interfejsy użytkownika zrealizowane przy użyciu JavaFX.
3. **Model** – logika gry, w tym zasady, punkty, statystyki i podpowiedzi.
4. **Zasoby** – pliki ze słowami dla różnych kategorii (np. `words.txt`, `animals.txt`, `technology.txt`).
5. **Style CSS** – style dla interfejsu użytkownika.


#  Funkcjonalnosci gry
##  Rozpoczęcie gry
- Zgadywanie liter przez kliknięcie przycisków **A-Z**.
- Możliwość użycia podpowiedzi (**Use Hint**), która odsłania losową literę w słowie.
- Liczba prób zależy od wybranego poziomu trudności.
- Podpowiedzi
- Timer

##  Ustawienia gry

### Poziom trudności:
-  **Easy** (8 prób)
-  **Medium** (6 prób)
-  **Hard** (4 próby)
-  **Extreme** (1 próba)

### Czas trwania gry:
-  **30 sekund**
-  **60 sekund**
-  **90 sekund**
-  **Opcja ustawienia własnego czasu**

### Źródło słów:
-  **Wczytywanie słów z pliku** (np. `words.txt` lub inne kategorie).
-  **Tworzenie nowego pliku** z własnymi słowami.
-  **Dodawanie słów** do istniejących plików.
-  **Kategorie ze słowami** gottowe do wykorzystania.

##  Statystyki
- **Wygrane gry** 
- **Przegrane gry** 
- **Aktualny wynik punktowy** 

##  Osiągnięcia
- Osiągnięcia za wygrane gry i wysokie wyniki.

##  Zasady gry
- Wyświetlane zasady gry w osobnym widoku.

##  Wymagania

- **Java 17** lub nowsza wersja
- **Maven** do zarządzania zależnościami
- **JavaFX** SDK


