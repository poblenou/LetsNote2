package com.project.letsnote.login;

import android.util.ArrayMap;

import java.util.ArrayList;

import java.util.Map;
import java.util.TreeMap;

public class User {
    public String name;

    public String email;

    public String facebookID;

    public String gender;

    public String pictureUrl;

    public String location;

    public ArrayList<String> seguidos = new ArrayList<>();
    public ArrayList<String> seguidores = new ArrayList<>();

    public ArrayList<String> notas = new ArrayList<String>();
    public ArrayList<String> fotos = new ArrayList<>();
    public ArrayList<String> video = new ArrayList<>();
    public ArrayList<String> texto = new ArrayList<>();

    public String descripcion;

    public User() {
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(ArrayList<String> seguidos) {
        this.seguidos = seguidos;
    }

    public ArrayList<String> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(ArrayList<String> seguidores) {
        this.seguidores = seguidores;
    }

    public ArrayList<String> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<String> fotos) {
        this.fotos = fotos;
    }

    public ArrayList<String> getVideo() {
        return video;
    }

    public void setVideo(ArrayList<String> video) {
        this.video = video;
    }

    public ArrayList<String> getTexto() {
        return texto;
    }

    public void setTexto(ArrayList<String> texto) {
        this.texto = texto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<String> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<String> notas) {
        this.notas = notas;
    }
}
