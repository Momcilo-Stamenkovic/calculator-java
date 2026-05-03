# TEST-RESULTS

## Predmet i nacin testiranja

Predmet testiranja je Calculator program koji se nalazi u ovom
repozitorijumu. Kalkulator radi tako sto korisnik unese kompletan
aritmeticki izraz (npr. `4+5` ili `10+5*4+3`) i program vraca
rezultat. Kalkulator treba da postuje prioritet operacija, sto
znaci da se mnozenje i deljenje racunaju pre sabiranja i oduzimanja.

Obavljeno je sistemsko testiranje, odnosno testiranje prihvatljivosti
po metodi crne kutije (black box). Tokom testiranja nisam gledao
izvorni kod, vec sam program testirao iz ugla korisnika - preko
ulaznih izraza i dobijenih rezultata. Pored toga, napisan je i
jedan jedinicni test (fajl `CalculatorTest.java`) po ugledu na
primer iz lekcije *Nivoi testiranja*, kojim se proverava osnovno
funkcionisanje metode `Calculate` (preko javne metode `Run`).


## Izrazi koji rade ispravno

Za pocetak sam isprobao niz tipicnih izraza koje korisnik moze da
unese, i tu je sve u redu. Izraz `4+5` daje rezultat `9.0`, izraz
`10-3` vraca `7.0`, `6*7` daje `42.0`, a `20/4` rezultat `5.0`.
Kada se kombinuju operacije razlicitog prioriteta, kalkulator se
ponasa ocekivano: za izraz `10+5*4+3` rezultat je `33.0`, a za
`2+3*4-1` rezultat je `13.0`. Lancano deljenje `100/10/2` daje
`5.0`, sto je takodje tacno (izvrsava se sa leva na desno). Ako
se unese samo jedan broj, npr. `5`, program vraca `5.0`. Decimalni
brojevi takodje rade, pa `1.5+2.5` daje `4.0`.

Pored ovih osnovnih slucajeva, kalkulator korektno radi sa
negativnim prvim operandom (`-5+3` vraca `-2.0`), sto je interno
reseno tako sto se na pocetak izraza dodaje nula. Razmaci oko
operatora su takodje u redu (`4 + 5` daje `9.0`), jer Java metoda
`Float.parseFloat` ignorise vodece i prateče razmake oko broja.


## Pronadjeni propusti i nedostaci

Najozbiljniji propust je da se prazan unos uopste ne kontrolise.
Ako korisnik samo pritisne Enter bez ikakvog teksta, program puca
sa `StringIndexOutOfBoundsException` zato sto se odmah na pocetku
poziva `expression.charAt(0)` bez prethodne provere duzine ulaza.
Ovo bi trebalo zameniti jasnom porukom korisniku da unos ne sme
da bude prazan.

Drugi ozbiljan problem je deljenje nulom. Kada se unese izraz
poput `5/0`, program ne baca gresku niti obavestava korisnika,
nego tiho vraca rezultat `Infinity`. Slicno tome, izraz `0/0`
daje rezultat `NaN`. U oba slucaja korisnik dobija pomalo
besmislen odgovor i lako moze da se zbuni - umesto toga, kalkulator
bi trebalo eksplicitno da prijavi da deljenje nulom nije dozvoljeno.

Otkriveno je takodje da kalkulator tiho prihvata izraze sa
operatorom na kraju. Ako se unese `5+`, korisnik bi ocekivao
gresku jer izraz nije kompletan, ali program prosto vrati `5.0`
kao da je drugi operand izostavljen i to ne smeta. Ista situacija
je i sa `5+5+`, gde se vraca `10.0` umesto poruke o gresci. U
ekstremu, ako se unese samo `+` ili samo `-`, kalkulator dodaje
nulu na pocetak, ostane samo trailing operator, koji se takodje
ignorise, i vraca se `0.0`. Korisnik tako dobija rezultat za
izraz koji uopste nije validan.

Kalkulator ne podrzava zagrade. Izraz tipa `(2+3)*4` vraca poruku
`ERROR`, sto je u redu jer korisnik dobija povratnu informaciju
da nesto nije kako treba, ali bi bilo zgodno kada bi i sama poruka
bila preciznija (npr. da kaze da zagrade nisu podrzane). Slicna
situacija je i sa modulo operacijom (`10%3`) i stepenovanjem
(`2^3`) - oba vraćaju `ERROR`. Ako bi se ove operacije dodale,
korisnost kalkulatora bi se znacajno povecala.

Decimalna zapeta umesto tacke takodje vodi do `ERROR` poruke (npr.
`1,5+2,5`). Ovo je korisno za primetiti zato sto u nasem govornom
podrucju je uobicajeno koristiti zapetu za decimale, pa korisnik
moze pomisliti da kalkulator ne radi, iako on naprosto ocekuje
tacku. Bilo bi dobro ili dodati podrsku za zapetu, ili korisnika
jasno obavestiti koja decimalna oznaka se koristi.

Posto je u kodu koriscen tip `float` (a ne `double`), preciznost
decimalnih brojeva nije najbolja. Tipican primer je izraz `0.1+0.2`,
gde rezultat nije tacno `0.3` zbog nacina na koji se realni brojevi
cuvaju u memoriji. Slicno, ako se unesu jako veliki brojevi, npr.
`9999999999*9999999999`, rezultat brzo izadje iz opsega tipa `float`
i dobija se priblizan, ne tacan rezultat. Za ozbiljniju primenu
trebalo bi razmotriti tip `double` ili `BigDecimal`.

Postoji jos jedna sitnija stvar koja je vise zapazanje nego pravi
bug - program nakon ispisivanja rezultata odmah ceka novi unos i
nema jasnu poruku korisniku da li unos vise nije validan ili je
sve protumaceno kako treba. To otezava razlikovanje validnih i
nevalidnih unosa pri brzom kuckanju.


## Zakljucak

Kalkulator radi solidno za jednostavne aritmeticke izraze sa
osnovnim operacijama, postuje prioritet operacija i dobro hvata
ocigledno neispravne unose (slova, zagrade, zapeta) tako sto
vraca rezultat `ERROR`. Glavni problemi su sto prazan unos rusi
ceo program, sto deljenje nulom prolazi bez ikakvog upozorenja
i sto se nepotpuni izrazi (npr. `5+`) tiho prihvataju kao da su
ispravni. Pre nego sto bi se ovaj kalkulator koristio u stvarnoj
primeni, te stvari bi trebalo srediti i, kad smo vec kod toga,
dodati podrsku za zagrade i precizniji tip podataka za decimalne
brojeve.
