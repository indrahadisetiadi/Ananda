package com.example.ging.starter_project.model;

public class Users {


    private static String id_user;
    private static String nama;
    private static String email;
    private static String password;
    private static String token;

    public Users(String id_user, String nama, String email, String password, String token) {
        this.nama = nama;
        this.email = email;
        this.token = token;
        this.id_user = id_user;
        this.password = password;
    }

    public Users() {

    }

    public static String getId_user() {
        return id_user;
    }

    public static void setId_user(String id_user) {
        Users.id_user = id_user;
    }

    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        Users.nama = nama;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Users.email = email;
    }

    public static String getToken() {
        return token;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Users.password = password;
    }

    public static void setToken(String token) {
        Users.token = token;
    }
}
