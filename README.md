calculator-java
Assignment - Metrika, pregled i staticka analiza
LOC
Brojao sam linije u Notepad++ (ima donji status bar gde pise broj linija).
Calculator.java - 188 linija
Start.java - 26 linija
Ukupno: 214 linija
Staticka analiza
Format: fajl - linija - zapazanje
Calculator.java - 6 - staticka promenljiva finalResult, ne valja jer je globalna
Calculator.java - 18 - metoda ToString pocinje velikim slovom, treba malim
Calculator.java - 24 - metoda Run pocinje velikim slovom
Calculator.java - 41 - u for petlji uslov i < length-1 preskoci poslednji karakter
Calculator.java - 63 - hvata se Exception (preopsiran), bolje NumberFormatException
Calculator.java - 74 - metoda Calculate previse duga, ima dosta ponovljenog koda
Calculator.java - 98 - ne proverava deljenje sa nulom
Start.java - 6 - promenljiva Expression treba da pocne malim slovom
Start.java - 12 - Scanner se pravi iznova u svakoj iteraciji while petlje
Start.java - 16 - close() na Scanner-u zatvara i System.in
