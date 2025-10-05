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

## INTRODUZIONE AL PROGETTO
L'obiettivo era di costruire un'applicazione *solida*, *scalabile* e *manutenibile*, per far ciò mi sono affidato a Maven e Spring MVC, pilastri dell'ecosistema Java.
    - Solidità
        - Tolleranza agli errori di input
        - Tolleranza ai fallimenti dei servizi
        - Gestione interna delle eccezioni
        - Coerenza e stabilità
    - Scalabilità:
        - Si riferisce alla capacità di un sistema di gestire un carico di lavoro crescente in modo efficiente.
        - Orizzontale -> Aumenta le risorse del server esistente
        - Verticale   -> Aggiunge più nodi (server) a un pool di risorse distribuendo il carico
    - Manutenibilità:
        - Correttività: facilità con cui è possibile individuare ed eliminare bug
        - Adattabilità: facilità con cui il software può essere modificato per funzionare in ambienti diversi
        - Perfettibilità: facilità con cui il software può essere migliorato on nuove feature (funzionalità)
        - Testabilità: facilità con cui il codice può essere verificato
### MAVEN
Popolare strumento di *build* e *gestione delle dipendenze*, dal lato build esso fornisce una struttura di progetto standard, dal lato della gestione dipendenze ha la capacita di gestire e includere automaticamente le librerie necessarie tramite il file pom.xml
### SPRING - MVC
Framework di sviluppo web, si basa sul pattern Model-View-Controller, i Model contengono i dati, i Controller gestiscono le richieste HTTP, le View restituiscono (nel nostro caso tramite Thymeleaf, template engine Java lato server che serve a creare pagine web che pur essendo dinamiche, possono essere visualizzate correttamente anche coome semplici file HTML statici in un browser)