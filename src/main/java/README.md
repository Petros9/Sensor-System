# Sensor-System
Prosty system do zarządzania czujnikami

System korzysta z modelu aktorów Akka (z uwagi, że ma symulować komunikację sieciową). Zdecydowałem się na taki model z uwagi na to, że Akka oferuje model asynchronicznej komunikacji, który może okazać się wartościowy w tym scenariuszu.

### Główna aplikacja 
Część składa się z poszczególnych klas:

- Dispatcher (pełni funkcję pośrednika, do którego podpięte są wszystkie czujniki),
- Monitor (pełni funkcję jednostki, która zadaje pośrednikowi różne zadania do zrealizowania),
- Main (główna klasa aplikacji, służąca do jej uruchomienia).

***Dispatcher***

* **Wątek:** `sendRequest`->
Wątek ma za zadanie w nieskończonej pętli pobierać przywołania wind. 

***Monitor***

* **Wątek:** `getResponse` ->
Wątek ma za zadanie informować użytkownika do jakiej ma się udać.


***Main***

### Station Properties

Część składa się z poszczególnych klas:

- Address (przechowuje dane lokalizacyjne stacji),
- Owner (przechowuje personalne dane właściciela stacji),


***Address*** 

***Owner***

### Utils