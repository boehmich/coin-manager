# Android Advanced - Final Project

## Coin Manager App

### Beschreibung

<p>Die App soll einen Überblick über gekaufte Kryptocoins bieten. 
<br>
<p>Dazu werden im CoinlistFragment die ersten 100 Coins der CoinmarketCap Api geladen und angezeigt (Retrofit). Aktiviert man im CoinlistFragment den Swiperefresher, holt sich die App die api ID, den Namen, das Symbol, den Slug, die prozentuelle Änderung der letzten Stunde, des letzten Tages, der letzten Woche und den aktuellen Wert der Coins.
Clickt man einen Coin der geladenen Liste an, wird man auf das CoinFragment weitergeleitet. Hier wird die Plattform (Kryptobörse), bei der man den ausgewählten Coin gekauft hat, angegeben. Kaufpreis und das Datum des Kaufes werden von der Coinlist übernommen, können aber geändert werden. Clickt man auf "add to watchlist" wird der Coin mit den angegeben Daten in der Watchlist gespeichert (GoogleRoom).
<hr>
<i>kurze Infos zu den Entitäten, weil das Naming sehr verwirrend ist: 
<br>Mit Coin meine ich den gekauften Coin. Da man mehrere Bitcoins, Ether,... zu verschiedenen Kursen kaufen kann, gibt es die Entität CoinApi, welcher nur einmal gespeichert wird. Über den Foreign Key (apiId) ist die Entität Coin mit der Entität CoinApi verbunden. Die Coins werden als CoinWithUpdate ausgeben, das ist im Endeffekt der gekaufte Coin mit dem CoinApi. 
<br>Beim Versuch diesen Namen (CoinWithUpdate) nachträglich zu ändern, hab ich mir zwei mal das ganze Projekt zerschossen, deswegen lasse ich es jetzt so drinnen. Ich entschuldige mich für die Verwirrung, es funktioniert bei mir!
<hr>
<p>Im WatchlistFragment werden alle gekauften Coins nach aufsteigender ID gelistet. Zusätzlich wird beim betätigen des Swiperefreshers der aktuelle Wert des Coins von der Api geladen (Retrofit) und der aktuelle Preis der CoinApis in GoogleRoom upgedated. In der Watchlist wird der aktuelle Preis, der Kaufpreis und die Veränderung in Prozent (diese wird immer neu berechnet) angegeben.
Drückt man auf einen Coin, kommt man zu einem Dialog mit der Auswahl "edit" und "delete". Edit führt zum CoinEditFragment, das das selbe Layout wie das CoinFragment verwendet. Hier kann man die Daten abändern und speichern (GoolgeRoom update). Mit "delete" wird der Coin gelöscht (Google Room delete). Der CoinApi bleibt weiterhin gespeichert!!!
<p>Die Map hat keine Verbindung mit dem Rest der App. Sie ist nur für den Block eingebaut worden. Kurze clicks auf die Map geben die geclickten Coordinaten aus. Lange Clicks erzeugen einen Marker, der danach auch wieder gelöscht werden kann.



## verwendete Blöcke
<p> <strong>Base<br>
<p> <strong>SQL with Google Room<br>
<p> <strong>Retrofit and JSON<br>
<p> <strong>Google Maps<br>

