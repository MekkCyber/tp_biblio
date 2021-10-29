package com.essai3.beans;

public class Librarian {
        private int id;
        private String nom, prenom, email, passwdhash;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPasswdhash() {
            return passwdhash;
        }

        public void setPasswdhash(String passwdhash) {
            this.passwdhash = passwdhash;
        }

        public Librarian(int id, String nom, String prenom, String email, String passwdhash) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.passwdhash = passwdhash;
        }
    }


