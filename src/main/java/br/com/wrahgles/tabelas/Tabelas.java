package br.com.wrahgles.tabelas;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class Tabelas {

	public static void main(String[] args) throws Exception{

		//cria as tabelas no banco que esta no arquivo hibernate.cfg
		new Tabelas().criarTabelas();
		//new Tabelas().deletarTabelas();
		
	}
	
	private SchemaExport getSchema(){
		Configuration conf = new AnnotationConfiguration();
		conf.configure();
		SchemaExport se = new SchemaExport(conf);
		return se;
	}
	
	public void criarTabelas(){
		getSchema().create(true, true);
	}
	
	public void deletarTabelas(){
		getSchema().drop(true, true);
	}
}
