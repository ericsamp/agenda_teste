package br.com.agenda.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.context.RequestContext;

import br.com.agenda.dao.ContatoDao;
import br.com.agenda.modelo.Contato;


@Named
@RequestScoped
public class ContatoBean  {
	

	
 @Inject
	private ContatoDao contatoDao;
		  
	private Contato contato = new Contato();	
		
	private List<Contato> contatos;
	private String nome; 
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Contato getContato() {
		  return contato;
	}
  
// #############Abrir dialog para atualizar contato ##############  
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		
		RequestContext.getCurrentInstance().openDialog("atualizar", opcoes, null);
	}
	
  // ################ Pesquisar  ###########
	public void pesquisar() {
		contatos = contatoDao.porNomeSemelhante(nome);
	}

	public void selecionar(Contato c) {
		RequestContext.getCurrentInstance().closeDialog(c);
	}
  
  
  // ################ Lista todos os  contatos  ###########
  public List<Contato> getContatos(){
	  if(this.contatos == null) {
		  this.contatos = contatoDao.listarTodos();
	  }
	  return contatos; 
  }
  
  // ################ Salvar contato  ###########
   public String save() {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contato Salvo"));
      contatoDao.inserir(contato);
      return "faces/contatos?faces-redirect=true";
  }
   
   public void update() {
	      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contato Atualizado"));
      contatoDao.atualizar(contato);
  }

 
 // ################ remover contato   ###############
  @Transactional
  public void remover(Contato contato) {
	  System.out.println("Removendo contato");
	  contatoDao.remove(contato);
	  this.contatos = contatoDao.listarTodos();
  }
 
  public void deletar() {
	 this.remover(contato);
      addMessage("Info", "Usuario deletado.");
  }
   
  public void addMessage(String summary, String detail) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
      FacesContext.getCurrentInstance().addMessage(null, message);
  }

  
  
}
