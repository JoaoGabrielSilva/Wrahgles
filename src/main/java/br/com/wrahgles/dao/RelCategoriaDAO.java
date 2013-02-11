package br.com.wrahgles.dao;

import java.util.List;

import br.com.wrahgles.model.RelCategoria;

public interface RelCategoriaDAO extends GenericoDAO<RelCategoria, Long> {

   public List<RelCategoria> findByIdCategoria(Long id);
   public RelCategoria findByIdCategoriaAndIdSubCategoria(Long idCategoria, Long idSubcategoria);
}
