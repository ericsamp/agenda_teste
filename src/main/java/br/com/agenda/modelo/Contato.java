package br.com.agenda.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contato {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String email;
	private String numeroContato;
	
 //--- Getter and Setter ----
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getNumeroContato() {
		return numeroContato;
	}
	public void setNumeroContato(String numeroContato) {
		this.numeroContato = numeroContato;
	}
	

}
