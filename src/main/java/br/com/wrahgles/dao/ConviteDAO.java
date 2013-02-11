package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.Convite;

public interface ConviteDAO extends GenericoDAO<Convite, Long>{
	
	public List<Convite> findByFlgEnvioEmail(boolean flgEnvioEmail);
	public List<Convite> findByEmailAndFlgEnvioEmail(String email, boolean flgEnvioEmail);
	public Convite findByIdUsuarioAndEmail(Long idUsuario, String email);
	public List<Convite> findByEmail(String email);

}
