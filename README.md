# Coin Manager App

### Beschreibung

<p>Die App soll einen Überblick über gekaufte Kryptocoins bieten. 
<br>
<p>Dazu werden im CoinlistFragment die ersten 100 Coins der CoinmarketCap Api geladen und angezeigt (Retrofit). Aktiviert man im CoinlistFragment den Swiperefresher, holt sich die App die api ID, den Namen, das Symbol, den Slug, die prozentuelle Änderung der letzten Stunde, des letzten Tages, der letzten Woche und den aktuellen Wert der Coins.
Clickt man einen Coin der geladenen Liste an, wird man auf das CoinFragment weitergeleitet. Hier wird die Plattform (Kryptobörse), bei der man den ausgewählten Coin gekauft hat, angegeben. Kaufpreis und das Datum des Kaufes werden von der Coinlist übernommen, können aber geändert werden. Clickt man auf "add to watchlist" wird der Coin mit den angegeben Daten in der Watchlist gespeichert (GoogleRoom).
