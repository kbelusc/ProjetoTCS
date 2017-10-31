package br.sc.senac.bean;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;







@ManagedBean
public class beanCadastrarPessoa implements Serializable{

	private static final long serialVersionUID = -2970865214288245193L;
	
	String nome;
	String email;
	String senha;
	
	
	
	public String salvarPessoa() { 
		return null;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

	 



}
