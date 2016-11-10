<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 权限递归 -->
<c:forEach items="${privileges}" var="privilege">
	<li>
		<c:set var="isShow" value="false" scope="request"/>
		<c:set var="exitId" value="${fn:length(sysDefaultUrls)}" scope="request"/>
		<c:forEach items="${sysDefaultUrls }" var="item" end="${exitId}">
				<c:if test="${privilege.id == item}">
					<c:set var="isShow" value="true" scope="request"/>
					<c:set var="exitId" value="0" scope="request" />
				</c:if>
		</c:forEach>
		<input id="cb_${privilege.id }" type="checkbox" name="privilegeIds" value="${privilege.id }" <c:if test="${isShow == true}">checked="checked"</c:if>>
		<label for="cb_${privilege.id }">${privilege.privilegename }&nbsp;&nbsp;&nbsp;&nbsp;${privilege.privilegeurl }</label>
		<c:choose>
			<c:when test="${fn:length(privilege.childrenPrivileges) > 0}">
				<ul>
					<c:set var="level" value="${level + 1}" scope="request" />
					<c:set var="privileges" value="${privilege.childrenPrivileges}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->
					<c:import url="/jsp/public/tokenPrivilege_ckbox.jsp" />
				</ul>
			</c:when>
		</c:choose>
	</li>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 -->