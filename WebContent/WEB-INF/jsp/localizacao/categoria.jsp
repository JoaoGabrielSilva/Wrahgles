<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<select class="option" name="localizacao.categoria.id" id="relCategoria">
  <option selected="selected" value="">(selecionar subcategoria) </option>
  
  <c:if test="${not empty relCategoriaList}">
    <c:forEach var="cat" items="${relCategoriaList}">
         <option value="${cat.id}">${cat.subCategoria.nome}</option>
    </c:forEach>
  </c:if>
</select>