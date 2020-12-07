////////////////////////////////////////////////////////////////////
// [DAMIANO] [BERTOLDO] [1193081]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

public class User {
    private String name;
    private String surname;
    private int userAge;

    public User(String name, String surname, int userAge) {
// Controllo che i dati siano corretti
        if (name == null) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Nome vuoto");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Nome ha solo spazi");
        }
        if (surname == null) {
            throw new IllegalArgumentException("Cognome non valido");
        }
        if (surname.isEmpty()) {
            throw new IllegalArgumentException("Cognome vuoto");
        }
        if (surname.isBlank()) {
            throw new IllegalArgumentException("Cognome ha solo spazi");
        }
        if (userAge <= 0 || userAge > 120) {
            throw new IllegalArgumentException("Data di nascita non valida");
        }

        this.name = name;
        this.surname = surname;
        this.userAge = userAge;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getUserage() {
        return userAge;
    }
}