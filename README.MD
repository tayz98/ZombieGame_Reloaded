# Dokumentation

## Autoren:
Alexander Nachtigal<br>
Sören Zacharias <br>
Veronica Zylla </p>
___
## Natürlich sprachliche Beschreibung des Programms
___
## Spielanleitung
siehe externe Datei **_Spielanleitung_**.
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
siehe externe Datei **_UML_Diagramm_**
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
  * Zweispielermodus [] (stattdessen Portale im Spiel)
  * Punktestand [x] (Punktestand oberhalb des Spielfeldes)
  * Grafische Oberfläche [x] (umgesetzt in GameBoard.java)
  * ~~Sinnvoller Einsatz zweier Design Patterns~~
___


