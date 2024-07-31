<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}" />
<style>
  a:link {  text-decoration: none; }
  a:visited {  text-decoration: none;}
  a:hover {  text-decoration: underline;}
  a:active {  text-decoration: underline;}
</style>

<section>
<br>
<div align=center>
<h2>답변형 게시판 목록보기 &emsp; </h2>

    1.페이지사이즈: ${pageSize }&emsp;
    2.페이지 List 사이즈: ${pageListSize }&emsp;
    3.전체레코드 수: ${totalCount }&emsp;
    4.총페이지수:${totalPage } <br>
    5.현재레코드: ${start }&emsp;
    6.현재페이지: ${currentPage }&emsp;
    7.하단가로 시작:${listStartPage }&emsp;
    8.하단가로 끝:${listEndPage} &emsp;
    9.마지막페이지의 첫번째 레코드 :${lastPage} &emsp;</b>
  
 <table border=1  width=1000>
 <tr align="center">
      <th align="center" width=70> idx </th><th> name  </th>  <th> memo  </th> 
      <th> age  </th>  <th> regdate  </th> 
     </tr>
<c:forEach items="${li}"  var="m" >
 <tr> 
      
      <td align="center" >  ${m.idx} </td> 
      <td align=center> ${m.name}   </td>
      <td align=center> ${m.memo} </td>
      <td align=center> ${m.age} </td>
      <td align=center> ${m.regdate} </td>

 </tr> 
</c:forEach>
</table>
 
 <c:url  value="/list"  var="url">
   <c:param name="start"  value="1"   />
   <c:param name="ch1"  value="${ch1}"   />
   <c:param name="ch2"  value="${ch2}"   />
 </c:url>
 <a href="${url}">처음으로</a> &emsp;
  
 <c:if test="${listStartpage > pageListSize }">
  <c:set var="start" value="${(listStartpage - pageListSize -1) * pageSize + 1 }" />
   <c:url  value="/list"  var="url">
	   <c:param name="start"  value="${start}"   />
	   <c:param name="ch1"  value="${ch1}"   />
	   <c:param name="ch2"  value="${ch2}"   />
   </c:url>
  <a href="${url}">이전${pageListSize}페이지</a> &emsp;
 </c:if>
 <c:if test="${listStartpage <= pageListSize }">
  이전${pageListSize}페이지&emsp;
 </c:if>
 
 
 <c:forEach var="i" begin="${listStartPage}" end="${listEndPage}" >
  <c:if test="${ i <= totalPage }">
	   <c:set var="start" value="${(i-1)*pageSize+1}" />
	   <c:url  value="/list"  var="url">
	   <c:param name="start"  value="${start}"   />
	   <c:param name="ch1"  value="${ch1}"   />
	   <c:param name="ch2"  value="${ch2}"   />
   </c:url>
   
	  [<a href="${url}">${i}</a>]
  </c:if>
 </c:forEach>
 
 
 <c:if test="${ listEndPage < totalPage }">
   <c:set var="start"  value="${listEndPage * pageSize + 1 }" />
   <c:url  value="/list"  var="url">
	   <c:param name="start"  value="${start}"   />
	   <c:param name="ch1"  value="${ch1}"   />
	   <c:param name="ch2"  value="${ch2}"   />
   </c:url>
	
   &emsp;<a href="${url}">다음${pageListSize}페이지</a> &emsp;
 </c:if>
  <c:if test="${ listEndPage >= totalPage }">
   &emsp;다음${pageListSize}페이지 &emsp;
 </c:if>
 
 
 <c:set var="start"  value="${lastPage}"   />
   <c:url  value="/list"  var="url">
	   <c:param name="start"  value="${start}"   />
	   <c:param name="ch1"  value="${ch1}"   />
	   <c:param name="ch2"  value="${ch2}"   />
   </c:url>
 
 	
 <a href="${url}">마지막으로 </a>

  <form action=/list>
  <input type=text name=start  value="1"  size=2> 
  <select  name=ch1>
   <option value="name"> 글쓴이 </option>
   <option value="memo"> 메모내용 </option>  
  </select>
  <input  type=text  name=ch2 >
  <input  type=submit value="검색하기" >
 </form>
 
</div>
<br>
</section>
</body>
</html>
