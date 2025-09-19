# BACKEND - Backoffice in Spring
Dovrete sviluppare un backoffice con autenticazione gestita da Spring Security. Una volta loggato, l'utente potrà gestire un'entità a scelta, come: 
- Videogiochi 🎮
- Film 🎬
- Album musicali 💿
- Libri o Fumetti📚
…o qualsiasi altra entità vi venga in mente!

Per questa entità dovrete implementare tutte le operazioni CRUD (Creazione, Lettura, Aggiornamento, Eliminazione).

Oltre a questa, dovrà esserci almeno una seconda entità collegata alla prima con una relazione 1-N o N-N.

Esempi:

- Se avete scelto i videogiochi, potreste avere la tabella delle console su cui è disponibile un gioco (PS5, Xbox, Switch).
- Se avete scelto i film, potreste collegarli ai generi cinematografici (Azione, Commedia, Horror).
- Potreste anche scegliere di avere 2 entità relazionate, ad esempio, nel caso di videogiochi, sia la console che il genere (Avventura, Picchiaduro, GDR)
Tutto il backoffice deve essere realizzato usando Thymeleaf, ma potete aiutarvi con JS per eventuali necessità di logiche frontend. Siete anche liberi di usare librerie JavaScript esterne se vi torna comodo. 

## Link al frontend:
[Frontend Repository](https://github.com/MegaEmme/frontend)