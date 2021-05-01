# Sensor-System
Prosty system do zarządzania czujnikami

System korzysta z modelu aktorów Akka (z uwagi, że ma symulować komunikację sieciową). Zdecydowałem się na taki model z uwagi na to, że Akka oferuje model asynchronicznej komunikacji, który może okazać się wartościowy w tym scenariuszu.

### Główna aplikacja 
Część składa się z poszczególnych klas:

- Dispatcher (pełni funkcję pośrednika, do którego podpięte są wszystkie czujniki),
- Monitor (pełni funkcję jednostki, która zadaje pośrednikowi różne zadania do zrealizowania),
- Main (główna klasa aplikacji, służąca do jej uruchomienia).

***Dispatcher***
Klasa ma za zadanie odbierać wszystkie zapytania i je przetwarzać (tak, aby była możliwa obługa zapytań z różnych stacji monitorujących).
Aktor odbiera komunikaty dziedziczące z interfejsu `DispatcherQuery`.
Sensory przechowywane są na hashmapie, gdzie kluczem jest id poszczególnego sensora.

* **Metoda:** `createReceive`->
Metoda w zależności od rodzaju otrzymanego komunikatu wykonuje różne akcje.

* **Metoda:** `onAddNewSensorQuery`->
Metoda ma za zadanie utworzyć w systemie nowy czujnik z odpowiednimi parametrami.

* **Metoda:** `onDeleteSensorQuery`->
Metoda ma za zadanie usunąć z systemu czujnik z określonym id (w przypadku braku czujnika z okreslonym id zostanie odesłany komunikat).

* **Metoda:** `onEditSensorQuery`->
Metoda ma za zmodyfikować czujnik z określonym id (w przypadku braku czujnika z okreslonym id zostanie odesłany komunikat). Zmodyfikowane zostaną te parametry, które nie mają wartości null.
Aby uniknąć potencjalnym anomalii podczas edycji mapa dostęp do czujników jest synchronizowany.

* **Metoda:** `onGetAllSensorsQuery`->
Metoda ma za zadanie zwrócić wszystkie czujniki będące w systemie (zwracane są przez utworzenie listy).

* **Metoda:** `onGetSensorByIdQuery`->
Metoda ma za zadanie zwrócić czujnik z określonym id (w przypadku braku czujnika z okreslonym id zostanie odesłany komunikat).


***Monitor***
Klasa ma za zadanie zadawać określone pytania do dispatchera w zależności od tego jakie otrzymała zapytania, jak i przetwarzać otrzymane odpowiedzi.
Aktor przetwarza komunikaty dziedziczące z interfejsu `MonitorQuery` -> znajdują się one w pakiecie `monitor_queries`.
* **Metoda:** `createReceive`->
Metoda w zależności od rodzaju otrzymanego komunikatu wykonuje różne akcje.

* **Metoda:** `onGetAllSensorsResponse`->
Metoda ma za zadanie odbierać listę ze wszystkimi czujnikami. Z racji braku innych pomysłów oraz chęci przetestowania poprawnego działania zdecydowałem się w niej wypisać wszystkie parametry dla każdego czujnika.
* **Metoda:** `onGetSensorByIdResponse`->
Metoda ma za zadanie odbierać czujnik z określonym id. W celach testowych wypisywane są wszystkie parametry czujnika.

* **Metoda:** `onSetMonitorNameQuery`->
Metoda ma za zadanie nadawać stacji monitorującej własną nazwę.

* **Metoda:** `onErrorResponse`->
Metoda ma za odebrać i wyświetlić komunikat o zaistniałym błędzie.

* **Metoda:** `onAddNewSensorMonitorQuery`->
Metoda ma za zadanie utworzyć i wysłać do dipatchera zlecenie utworzenia nowego czujnika. Id generowane jest przy pomocy losowego wygenerowania tekstu przez klasę UUID z dodaniem nazwy stacji (może potencjalnie zapewnić większą losowość, ponadto wiadomo, jaka stacja utworzyła dany czujnik).

* **Metoda:** `onDeleteSensorMonitorQuery`->
Metoda ma za zadanie utworzyć i wysłać do dipatchera zlecenie usunięcia czujnika z określonym id.

* **Metoda:** `onEditSensorMonitorQuery`->
Metoda ma za zadanie utworzyć i wysłać do dipatchera zlecenie zmodyfikowania czujnika z określonym id.

* **Metoda:** `onGetAllSensorsMonitorQuery`->
Metoda ma za zadanie utworzyć i wysłać do dipatchera zlecenie przesłania do danej stacji wszystkich czujników z systemu.

* **Metoda:** `onGetSensorByIdMonitorQuery`->
Metoda ma za zadanie utworzyć i wysłać do dipatchera zlecenie przesłania do danej stacji czujnika z określonym id.

***Main***
W klasie uruchamaina jest aplikacja. Tworzony jest aktor Main przyjmujący komunikaty o typie `Void`
W funkcji create tworzony jest nieoficjalny scenariusz testowy, który polega utworzeniu dwóch stacji i jednego dispatchera, a następnie:
- dodaniu trzech stacji,
- modyfikacji jednego z czujników,
- usunięcia jednego czujnika,
- pobrania informacji o jednym z czujników.
Po każdym działaniu w formie testu wypisywane są informacje wszystkich czujników znajdujących się w systemie.

### Station Properties

Część składa się z poszczególnych klas:

- Address (przechowuje dane lokalizacyjne stacji),
- Owner (przechowuje personalne dane właściciela stacji),


***Address*** 
Klasa ma zadanie enkapsulować informacje na temat adresu.
Adres posiada takie informacje jak: ulica, numer pocztowy z miastem, jak i kraj przebywania czujnika.
***Owner***
Klasa ma zadanie enkapsulować informacje na temat właściciela czujnika.
Właściciel posiada następujące parametry: imię i nazwisko. 

### Utils

***AddNewSensorQuery*** 


***DeleteSensorQuery*** 


***EditSensorQuery*** 


***ErrorResponse*** 

***GetAllSensorsQuery*** 

***GetAllSensorsResponse*** 

***GetSensorByIdQuery*** 

***GetSensorByIdResponse*** 

***Sensor*** 