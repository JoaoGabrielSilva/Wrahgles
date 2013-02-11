package br.com.wrahgles.testes;

import java.io.IOException;

import br.com.wrahgles.properties.AdminProperties;
import br.com.wrahgles.properties.ContatoDenunciaProperties;
import br.com.wrahgles.properties.PerfilProperties;
import br.com.wrahgles.properties.UsuarioProperties;

public class TestaProperties {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		UsuarioProperties userProps = new UsuarioProperties();
		System.out.println("UsuarioProperties");
		System.out.println(userProps.getBem_vindo());
		System.out.println(userProps.getCampo_email_obrigatorio());
		System.out.println(userProps.getArquivo_invalido());
		System.out.println(userProps.getCadastro_wrahgles());
		System.out.println("");
		PerfilProperties perfilProps = new PerfilProperties();
		System.out.println("PerfilProperties");
		System.out.println(perfilProps.getConf_amizade());
		System.out.println(perfilProps.getErro_add_lista_amigo());
		System.out.println(perfilProps.getErro_conf_voce_mesmo());
		System.out.println("");
		AdminProperties adminProps = new AdminProperties();
		System.out.println("AdminProperties");
		System.out.println(adminProps.getCadastro_subcategoria());
		System.out.println(adminProps.getCampo_mensagem_obrigatorio());
		
		System.out.println("");
		System.out.println("ContatoDenunciaProperties");
		ContatoDenunciaProperties contDenunProps = new ContatoDenunciaProperties();
		
		System.out.println(contDenunProps.getCampo_mensagem_obrigatorio());
		System.out.println(contDenunProps.getDenuncia());

	}

}
