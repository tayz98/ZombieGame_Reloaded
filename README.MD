# Dokumentation
___
## Autoren:
Alexander Nachtigal<br>
Sören Zacharias <br>
Veronica Zylla </p>

___
## Natürlich sprachliche Beschreibung des Programms
___
___
## Spielkonzept
___
In einem von vier verschiedenen Schwierigkeitsgraden ist es Ziel des Spiels Heilmittel bzw. „Remedy“ einzusammeln und im Anschluss den Ausgang zu erreichen,  ohne dabei von den Zombies oder Jumper Zombies gefressen zu werden.  
Wird man von einem Zombie gefangen und verspeist hat man das Spiel verloren.  
Man bewegt die Spielfigur, den sogenannten „Survivor“ über ein Spielfeld (mit vorab definierter Größe) und umgeht dabei die Hindernisse.  
Man kann die Spielfigur auf dem Spielfeld in vier verschiedene Richtungen bewegen und außerdem die Portale nutzen um zu einem anderen Portal und somit zu einem anderen Punkt auf dem Spielfeld zu gelangen.  
Um den Zombies zu entkommen, welche sich ebenfalls auf dem Spielfeld bewegen, kann man zudem das Flash-Item einsammeln und aktivieren und dadurch die Geschwindigkeit des Survivors temporär erhöhen.  
Für jeden Schritt, sowie das Einsammeln der Heilmittel und Items und das Erreichen des Ausgangs erhält man Punkte, somit kann man das Spiel nicht nur gewinnen oder verlieren sondern auch immer höhere Scores aufstellen.

Das Spiel lässt sich auf drei verschiedenen Schwierigkeitsstufen (EASY,MEDIUM,HARD) 
und auf zwei unterschiedlichen Oberflächen (Konsole, grafisch) spielen.
Es können auch Custom Settings benutzt werden.

### Erhaltene Punkte:
* Ein Schritt: 10 Punkte
* Einsammeln des Items: 25 Punkte
* Einsammeln eines Heilmittels: 50 Punkte
* Erreichen des Ausgangs: 100 Punkte
___
## Spielsymbole: 
___

### Konsole: 
* Survivor: **S**
* Zombie: **Z**
* Jumper: **ZJ**
* Remedy: **⚕**
* Exit: **#**
* Portal: **o**
* Flash: **F**
* Obstacle: **X**

### Grafisch: 

* Survivor: 
![Survivor](/playfield/images/survivor.png)
* Zombie:
![Zombie](/playfield/images/zombieNormal.png)
* Jumper: 
![Jumper](/playfield/images/ZombieJumper.png)
* Remedy: 
![Remedy](/playfield/images/remedy.png)
* Exit: 
![Exit](/playfield/images/exit.png)
* Portal:
![Portal](/playfield/images/portal.png)
* Flash:
![Flash](/playfield/images/flash.png)
* Obstacle:
![Obstacle](/playfield/images/obstacle.png)



## Steuerung
___

### Bewegung:
* Nach oben: **W**
* Nach unten: **S**
* Nach links: **A**
* Nach rechts: **D**

### Sonstiges:
* Item aktivieren: **E**
* Spiel verlassen: **Q**



## Erfahrungsbericht
Erfahrungsbericht zur Portierung des schon vorhandenen Programmes
___
Wir haben uns dazu entschieden, den schon vorhandenen Code aus der alten Aufgabe zunächst als Vorlage zu übernehmen.  
Das bot sich vor allem an, weil wir zu dieser Zeit die Einstellungen schon als Klasse deklariert und genutzt haben.  
Wir haben damals auch schon versucht, einige Features einzubauen, auf die wir im Laufe dieser Ausarbeitung zurückgreifen konnten.

Da die Spielobjekte (Survivor, Zombie etc.) schon damals als Instanzen von _Point_-Objekten realisiert wurden, war der Umbau relativ intuitiv und naheliegend.  
Dazu haben wir eine abstrakte Superklasse gebaut, die von der Klasse _Point_ erbt und dessen Eigenschaften besitzt.  
Somit konnten wir die Design-Elemente größtenteils übernehmen und mussten nicht von vorne anfangen.  
Durch das Erstellen von neuen Unterklassen, die von der _Point_-Klasse erben, hatten wir die Möglichkeit, alle Methoden in die Klassen auszulagern und den Objekten zuzuordnen (bspw. _move_), die die jeweiligen Methoden nutzen.

Alles in allem haben wir die Funktionalitäten und Strukturen unseres alten Programmes größtenteils übernommen und OO-Konzepte da angewendet, wo es sinnvoll ist, um ein besser strukturiertes Programm zu bekommen.  
Die deklarierten Methoden wurden den Objekten zugeordnet, bei denen die Methoden Sinn ergeben und der gesamte Ablauf der main-Methode wurde in eine eigene Instanz vom Spielobjekt **ZGame** verlagert.



## UML
___
[Link zur Datei](changeme)


## Anforderungen
<hr>

* Pflicht-Anforderungen
    * Dokumentation des Programms [x]
    * UML Diagram [x]
    * Natürlichsprachliche Beschreibung des Programmaufbaus []
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
  * Direkte Abfrage der Tastatureingabe (Event) [x]

* Wahlanforderungen (mindestens 2 aus der Liste)
  * Zusätzlicher Monstertyp [x]
  * Einsammelbares Powerup mit hilfreicher Funktionalität für den Spieler [x]
  * Einstellbarer Schwierigkeitsgrad [x]
  * Hindernisse, z.B. Felsen oder Mauern [x]
  * Zweispielermodus [ ]
  * Punktestand [x]
  * Grafische Oberfläche [x]
  * ~~Sinnvoller Einsatz zweier Design Patterns~~
___



<style type="text/css" rel="stylesheet">
img { width: 100px;
        height: 150px;
}
</style>

