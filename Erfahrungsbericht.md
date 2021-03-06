# Erfahrungsbericht
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
