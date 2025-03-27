package model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataMatricula;
    private String curso;

    // Construtor padrão
    public Student() {}

    // Construtor com parâmetros
    public Student(int id, String nome, String email, String telefone, LocalDate dataMatricula, String curso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataMatricula = dataMatricula;
        this.curso = curso;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}