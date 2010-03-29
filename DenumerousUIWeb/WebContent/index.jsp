<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--
  Copyright 2009 State Farm Insurance Co.
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
    http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Denumerous - Test Combination Generator</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>

<h1 align="center">Denumerous</h1>
<center><pre>
<a href="index.jsp" target="_self">Home</a>  <a href="help.jsp" target="_self">Help</a>
</pre></center>

<%
	if(session.getAttribute("error") == "true") {
%>
	<div id="error"><b>Error:</b> <%=session.getAttribute("errorMsg") %>.</div>
	<% session.setAttribute("error", "false"); %>
<%	} 
%>



<form method="POST" action="combinations.jsp">
<center>
	<textarea rows="25" cols="100" name="inputParameters"></textarea>
	<br><br>

	<b>Select Generator: </b>
	<select name="combinationGenerator">
		<option value="allpairs1">All Pairs</option>
		<option value="allcombinations">All Combinations</option>
	</select>
	
	<input type="submit" name="generate" value="Generate Combinations">
</center>
</form>

</body>
</html>
