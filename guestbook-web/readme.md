# Guestbook Web

Diese Projekt dient als Web-Projekt für die Verwaltung von Gästebuch einträrgen. 
Für das Lesen und die Anlage von Gästebuch-Einträgen muss ein nutzer zunächt angemeldet werden.

Folgende Nutzer (benutzername:password) sind nach dem Start der Applikation im System vorhanden:

* user:user
* admin:admin

Für das Speichern der Nutzer wird eine In-Memory-Liste verwendet.


Für das Speichern von GästebBuch-Einträgen wird das Projekt guestbook-rest mittels JSON-Rest-Aufrufen verwendet. (Siehe de.hsw.jee.guestbook.web.services.rest.GuestbookServiceRest.java). Dabei ist zu berücksichtigen, dass die Formate der Gästebuch-Einträge in den beien Projekten divergieren, weshalb innerhalb des Services ein Mapping stattfinden muss.


