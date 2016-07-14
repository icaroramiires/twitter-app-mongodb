package br.edu.ifba.curso.webservice.mongo;

import java.util.List;

public class Usuario {
	private String id;
	private String nome;
	private String userName;
	private String email;
	private String senha;
	private List<Twitte> twittes;
	
	public Usuario(){}
	
	public Usuario(String nome, String userName, String email, String senha) {
		this.nome = nome;
		this.userName = userName;
		this.email = email;
		this.senha = senha;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}