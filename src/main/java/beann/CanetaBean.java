 package beann;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CanetaDAO;
import entidades.Caneta;

@ManagedBean
public class CanetaBean {
	private Caneta caneta = new Caneta();
	private List<Caneta> lista = new ArrayList<Caneta>();
	
	public String salvar() {
		CanetaDAO.salvar(caneta);
		caneta = new Caneta();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Caneta salva com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return null;
	}
	public void excluir(Caneta caneta) {
	    CanetaDAO.excluir(caneta);
	    lista.remove(caneta); // Remove a caneta da lista
	    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso", "Caneta excluida com sucesso");
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	} 
	
	public Caneta getCaneta() {
		return caneta;
	}

	public void setCaneta(Caneta caneta) {
		this.caneta = caneta;
	}

	public List<Caneta> getLista() {
		lista = CanetaDAO.listar();
		return lista;
	}

	public void setLista(List<Caneta> lista) {
		this.lista = lista;
	}
	
	public void selecionar(Caneta c) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
	            "ID: "+c.getId()+" / Marca: " + c.getMarca() + "  /  Modelo: " + c.getModelo() + "  /  Cor: " + c.getCor() ));
	}
	
	public void contarPorCor() {
	    int countAzul = 0;
	    int countPreta = 0;
	    int countVermelha = 0;

	    for (Caneta c : lista){
	        if ("azul".equalsIgnoreCase(c.getCor())) {
	            countAzul++;
	        } else if ("preta".equalsIgnoreCase(c.getCor())) {
	            countPreta++;
	        } else if ("vermelha".equalsIgnoreCase(c.getCor())) {
	            countVermelha++;
	        }
	    }

	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
	            "Quantidade de Canetas: " + countAzul + " azuis, " + countPreta + " pretas, " + countVermelha + " vermelhas"));
	}
}
