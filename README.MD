# Dokumentation

## Autoren:
Alexander Nachtigal<br>
Sören Zacharias <br>
Veronica Zylla </p>
___
## Natürlich sprachliche Beschreibung des Programms
Im Nachfolgenden werden wir den Ablauf des Programmes im Fenstermodus mit grafischer Oberfläche erklären:
Das Spiel wird durch die main-Methode aus der "GameBoard"-Klasse gestartet (Alternativ als Konsolen-Anwendung in der "ZombieGame"-Klasse).
Die Variablen sind außerhalb der main-Methode deklariert und teils instantiiert, damit sie in den Methoden von Processing zur Verfügung stehen.
Dort wird zunächst eine Instanz vom Board-Objekt mit den Maßen für das Spielfeld erstellt.
Dieses wird dann an die Instanz vom ZGame-Objekt übergeben und dort als Attribut geführt.
Mit der Instanz vom ZGame-Objekt wird das Spiel definiert.
In diesem Objekt werden auch die Settings, das Board und die ganzen Spielelemente geführt.
Nun wird beim Start des Spieles zunächst einmal ein Intro-Bildschirm präsentiert, bei dem man durch Menüführung die Informationen zu den Spielelementen und der Steuerung präsentiert und den Schwierigkeitsgrad einstellen kann.
Man kann zwischen _Leicht_, _Mittel_ und _Schwierig_ (Easy, Medium und Hard) wählen.
Nach Bestätigen des Schwierigkeitsgrades wird dann auch die Instanz des Settings-Objektes erstellt und somit festgelegt, wie schwer das Spiel für den Spieler sein soll.
Das wird definiert durch beispielsweise Anzahl der Heilmittel, die eingesammelt werden müssen oder Anzahl an Zombies (Genauere Informationen dazu in der Spielanleitung).
Anhand dieser Einstellungen wird ein Spielfeld generiert, das alle Spielelemente so positioniert, dass keine Elemente kollidieren und das Spiel für den Spieler gewinnbar ist. <p>

Nun befinden wir uns im Spiel, bei dem der Spieler seine Figur mithilfe der Tastatureingabe steuern kann und versucht das Spiel zu gewinnen.
Nach jedem Zug werden die Zombies bewegt (sofern sie nicht mehr schlafen) und überprüft, ob der Spieler das Ziel mit allen Heilmitteln erreicht oder ein Zombie seine Position erreicht hat.
Es wird zusätzlich überprüft, ob der Spieler einen legitimen Spielzug machen möchte.
Tut er dies nicht (zum Beispiel beim Laufen gegen ein Hindernis oder beim Erreichen des Spielfeldrandes), so wird keine Bewegung durchgeführt.
Weiterhin wird überprüft, ob die Position des Spielers mit Portalen oder Items übereinstimmt und dann gegebenenfalls Portale benutzt oder Items aufgesammelt.
Die Informationen über die Items stehen dem Spieler oberhalb des Spielfeldes zur Verfügung, wo auch seine Punktezahl dargestellt wird, die sich nach den Regeln aus der Spielanleitung berrechnen.
Nutzt der Spieler zum Beispiel ein Item, so wird seine Geschwindigkeit für 3 Runden auf 2 Felder pro Zug erhöht, was ebenfalls im Infobereich über dem Spielfeld angezeigt wird.

Durch Processing wird das Spielfeld permanent neu gezeichnet. Durch die Variable _gameState_ wird bestimmt, in welchem Stadium des Spiels man sich befindet.
Gewinnt oder verliert man das Spiel, erscheint eine Nachricht auf dem Bildschirm und das Spiel ist vorbei. Man hat dort nun die Möglichkeit, das Programm zu beenden oder das Spiel von vorne zu starten.
___
## Spielanleitung
siehe Datei **_Spielanleitung_**.
___
## Erfahrungsbericht
Erfahrungsbericht zur Portierung des schon vorhandenen Programmes

Wir haben uns dazu entschieden, den schon vorhandenen Code aus der alten Aufgabe zunächst als Vorlage zu übernehmen.  
Das bot sich vor allem an, weil wir zu dieser Zeit die Einstellungen schon als Klasse deklariert und genutzt haben.  
Wir haben damals auch schon versucht, einige Features einzubauen, auf die wir im Laufe dieser Ausarbeitung zurückgreifen konnten.

Da die Spielobjekte (Survivor, Zombie etc.) schon damals als Instanzen von _Point_-Objekten realisiert wurden, war der Umbau relativ intuitiv und naheliegend.  
Dazu haben wir eine abstrakte Superklasse gebaut, die von der Klasse _Point_ erbt und dessen Eigenschaften besitzt.  
Somit konnten wir die Design-Elemente größtenteils übernehmen und mussten nicht von vorne anfangen.  
Durch das Erstellen von neuen Unterklassen, die von der _Point_-Klasse erben, hatten wir die Möglichkeit, alle Methoden in die Klassen auszulagern und den Objekten zuzuordnen (bspw. _move_), die die jeweiligen Methoden nutzen.

Alles in allem haben wir die Funktionalitäten und Strukturen unseres alten Programmes größtenteils übernommen und OO-Konzepte da angewendet, wo es sinnvoll ist, um ein besser strukturiertes Programm zu bekommen.  
Die deklarierten Methoden wurden den Objekten zugeordnet, bei denen die Methoden Sinn ergeben und der gesamte Ablauf der main-Methode wurde in eine eigene Instanz vom Spielobjekt **ZGame** verlagert.
---
## UML
siehe Datei **_UML_Diagramm_**
___
## Anforderungen

* Pflicht-Anforderungen
    * Dokumentation des Programms [x]
    * UML Diagram [x]
    * Natürlichsprachliche Beschreibung des Programmaufbaus [x]
    * Kurze Spielanleitung [x]
    * JavaDoc für alle Klassen und mindestens alle public Methoden [x]
    * Kurzer Erfahrungsbericht, wie gut das alte Programm portiert werden konnte [x]

* technische Anforderungen
  * Keine Nutzung von Arrays [x]
  * Nutzung von OO-Konzepten, wo es sinnvoll ist [x]
  * Das Programm soll möglichst erweiterbar sein [x]
  * Mindestens eine abstrakte Klasse, von der mindestens zwei instantiierbare Klassen abgeleitet werden [x]
  * Mindestens zwei abstrakte Methoden [x]
  * Mindestens eine überladene Methode [x]
  * Mindestens eine Enumeration [x]
  * Mindestens eine selbst definierte Exception [x]
  * Direkte Abfrage der Tastatureingabe (Event) [x] (umgesetzt in der grafische Spielvariante)

* Wahlanforderungen (mindestens 2 aus der Liste)
  * Zusätzlicher Monstertyp [x] (2 Zombie Typen im Spiel)
  * Einsammelbares Powerup mit hilfreicher Funktionalität für den Spieler [x] (Flash-Item)
  * Einstellbarer Schwierigkeitsgrad [x] (EASY, MEDIUM und HARD)
  * Hindernisse, z.B. Felsen oder Mauern [x] (Hindernisse sind im Spiel)
  * Zweispielermodus [] (stattdessen Portale im Spiel + Maussteuerung)
  * Punktestand [x] (Punktestand oberhalb des Spielfeldes)
  * Grafische Oberfläche [x] (umgesetzt in GameBoard.java)
  * ~~Sinnvoller Einsatz zweier Design Patterns~~
___


