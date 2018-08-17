package br.com.agenda.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;



import br.com.agenda.modelo.Contato;

@Stateless
@SuppressWarnings("serial")
public class ContatoDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	private DAO<Contato> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Contato>(this.em, Contato.class);
	}

	public boolean existe(int id){
		JOptionPane.showMessageDialog(null, em.find(Contato.class, id));
		return false;
	}

	public List<Contato> porNomeSemelhante(String nome) {
		return em.createQuery("from Contato where nome like :nome", Contato.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
	public void inserir(Contato contato) {
		em.persist(contato);
	}
	public Contato carregarContato(Integer id) {
		return this.em.find(Contato.class, id);
	}
	public void remove(Contato c) {
		dao.remove(c);
	}

	public void atualizar(Contato c) {
		dao.atualiza(c);
	}
	
	public List<Contato> listarTodos(){
		return em.createQuery("from Contato", Contato.class).getResultList();
	}
	
}
