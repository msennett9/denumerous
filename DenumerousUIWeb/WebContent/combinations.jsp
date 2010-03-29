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

<%
	String parameters = request.getParameter("inputParameters");
	String generator = request.getParameter("combinationGenerator");
%>

<%@page import="java.io.StringWriter"%>
<%@page import="com.sf.denumerous.*"%>
<%@page import="com.sf.denumerousui.*" %>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Denumerous Results</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>

<h1 align="center">Denumerous Results</h1>
<center><pre>
<a href="index.jsp" target="_self">Home</a>  <a href="help.jsp" target="_self">Help</a>
</pre></center>

<%
	ResultsController r = new ResultsController(parameters, generator);
	
	TestCombinationList combinations = r.generateCombinations();
	if(!r.error) 
	{
		StringWriter sWriter = new StringWriter();
		ServletContext context = getServletContext();
		String xslSource = context.getRealPath("/");
		xslSource = xslSource + "\\combinationsToHtml.xsl";
	
		r.convertResultsToHtml(combinations, sWriter, xslSource);
		
		if(r.error) 
		{
			session.setAttribute("errorMsg", r.errorMsg);
			session.setAttribute("error", "true");
			response.sendRedirect("index.jsp");
		}
		else
		{
			String xmlCombinations = r.convertResultsToXML(combinations);
			session.setAttribute("combinationsXML", xmlCombinations);
			
			String csvCombinations = r.convertResultsToCSV(combinations);
			session.setAttribute("combinationsCSV", csvCombinations);
			
			out.println(sWriter.toString());
		}
	}
	else
	{
		session.setAttribute("errorMsg", r.errorMsg);
		session.setAttribute("error", "true");
		response.sendRedirect("index.jsp");
	}
	
%>

<form method="POST" action="export.jsp">
<center>
	<b>Export Results: </b>
	<select name="outputType">
		<option value="csv">CSV File</option>
		<option value="xml">XML File</option>
	</select>
	
	<input type="submit" name="export" value="Export Combinations">
</center>
</form>
</body>
</html>