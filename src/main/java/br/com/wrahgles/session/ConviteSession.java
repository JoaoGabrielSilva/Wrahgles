package br.com.wrahgles.session;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class ConviteSession implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> listConvites;
	
	public ConviteSession(){}

	public List<String> getListConvites() {
		return listConvites;
	}

	public void setListConvites(List<String> listConvites) {
		this.listConvites = listConvites;
	}
	
	
	
	
	
	
	
	

}
