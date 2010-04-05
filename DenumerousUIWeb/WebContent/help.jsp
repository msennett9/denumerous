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
<title>Denumerous Help Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>

<h1 align="center">Denumerous Help Page</h1>
<center>
<pre>
<a href="index.jsp" target="_self">Home</a>  <a href="help.jsp" target="_self">Help</a>
</pre></center>

<pre>
<b>Overview</b>

   Denumerous is a test combination generation tool.  It can be used to
generate a list of test cases given a set of parameters and possible values for
each parameter.  Several generation algorithms are supported.  Typically, the goal
is to reduce the total possible number of test cases by generating, for instance,
combinations that exercise all two-way interactions.  This is based on evidence
that many defects are related to the interactions of a small number of variables.
</pre>
<hr>

<pre>
<b>How to Use</b>

   The easiest way to input the test parameters and their values is to first enter 
them in a spreadsheet and copy them to the text area.  You could also copy them 
from a text editor or type them straight into the text area.  The input format is simple:
parameter names start in the first position; values for that parameter follow 
on subsequent lines indented with either a tab or spaces.
</pre>

<table width="700" cellspacing="8">
	<tr>  
		<td>Excel Example:</td>
		<td>Notepad Example:</td>
	</tr>
	<tr>
		<td><img src="ExcelSample.jpg" alt="Excel Sample"></td>
		<td><img src="NotepadSample.jpg" alt="Notepad Sample"></td>
	</tr>
</table>

<pre>
   Next select your parameters and copy them over to the text area on the home page.
Now click the "Generate Combinations" button.
</pre>
<img src="DenumerousSample.jpg" alt="Denumerous Sample">

<pre>
   On the next screen you will see a table displaying the generated combinations.  A value
of "empty" indicates a don't care value.  From this screen you can export your results to an 
XML and/or CSV file using the Export button.
</pre>
<img src="ResultsSample.jpg" alt="Results Sample"><br><br>

</body>
</html>
