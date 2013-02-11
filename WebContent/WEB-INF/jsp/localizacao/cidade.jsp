<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <input type="hidden" name="idEstado" id="idEstado" value="${idEstado}"/>
    <input type="text" id="cidade-local" name="localizacao.cidade" maxlength="150" value="" onchange="javascript:pesquisarLocalizacao();"/>
    <script type="text/javascript" src="<c:url value="/js/localizacao/autocomplete.cidade.js" />" charset="utf-8"></script>