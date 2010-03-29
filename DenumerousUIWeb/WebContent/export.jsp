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

<%@page language="java" session="true" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="java.io.PrintWriter"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>export</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>

<%
	String outputType = request.getParameter("outputType");
	String outputText = "";
	String downloadFileName = "";
	
	if (outputType.equals("xml")) {
		outputText = (String) session.getAttribute("combinationsXML");
		response.setContentType("text/xml");
		downloadFileName = "combinations.xml";
	}
	else {
		outputText = (String) session.getAttribute("combinationsCSV");
		response.setContentType("text/plain");
		downloadFileName = "combinations.csv";
	}
	
	response.setHeader ("Content-Disposition", "attachment;filename=\""+downloadFileName+"\"");
	response.setContentLength(outputText.length());

	PrintWriter outp = response.getWriter(); 
	outp.print(outputText);

    outp.flush();
    outp.close();
%>

</body>
</html>
