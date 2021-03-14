[![Build Status](https://travis-ci.org/PatrickJahn/ca3.svg?branch=master)](https://travis-ci.org/PatrickJahn/ca3)

## CA3 - How to use
### Preconditions
*In order to use this code, you should have a local developer setup + a "matching" droplet on Digital Ocean as described in the 3. semester guidelines.* 

### Dette skal ændres
Inden du begynder skal du oprette en ny database (med tilhørende test database) og ændre Persistence unit'en til at pege derhen. Derefter skal du ændre "remote.server" i pom filen til dit eget domænenavn med manager/text efterfulgt. <br>
I travis.yml filen, skal du ændre "CREATE DATABASE" til at oprette en med samme navn, som det du kaldte din lokale test database. <br>
Inde på din egen server skal du ændre i docker-compose.yml filen. Her kan du endte tilføje en ny "CONNECTION_STR" (fx kalde den "CONNECTION_STR2") eller bare ændre dens værdi til "jdbc:mysql://db:3306/XXX", hvor XXX er det navn du gav din lokale database. Hopper du så ind i projetet under utils ligger der en EMF_creator, som du skal gå ind i og ændre der hvor der står "System.getenv("CONNECTION_STR3")", til det du har kaldt din CONNECTION_STR på din egen server.

### Brug af startcoden
Projektet har følgende fungerende endpoints til at starte med: 
- "api/login": Bruges til at logge en bruger ind med> Gemmer en token i localstorage.
- "api/info/user": Kan kun vises hvis man er logget ind med rollen user
- "api/info/admin": Kan kun vises hvis man er logget ind med rollen admin
- "api/info/filmsparallel": Kan kun vises hvis man er logget ind med rollen admin eller user. Henter data fra anden server og retunere det som JSON.
<br>
For at hente data fra endpoint liggende på andre servere, bruges klassen HttpUtils. Og hvis du skal bruge threads, kan du meget belejligt ligge din callable klasse i filen CallableHandling under facade package. 


# Getting Started

This document explains how to use this code (build, test and deploy), locally with maven, and remotely with maven controlled by Travis
 - [How to use](https://docs.google.com/document/d/1K6s6Tt65bzB8bCSE_NUE8alJrLRNTKCwax3GEm4OjOE/edit?usp=sharing)
