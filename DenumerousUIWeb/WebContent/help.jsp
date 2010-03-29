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

   Denumerous is an application used to reduce the number of test cases needed to
be performed using an All Pairs algorithm.  The majority of errors are not caused by
a single input parameter value rather by the interaction between two or more.  All
Pairs is designed to reduce the number of total tests by only testing the interaction
between two parameter values one time.  Testing every combination will test each
interaction multiple times drastically increasing the number of tests needed to be
performerd.
</pre>
<hr>

<pre>
<b>How to Use</b>

   The easiest way to input the test parameters and their values is to first write 
them in a Microsoft Excel spreadsheet and copy them to the text area.  You could 
also copy them in from a text editor or type them straight into the text area.  The 
input format is to keep the parameter names left aligned and indent their value's 
names on subsequent lines immediately following the parameter's line using either 
a tab or spaces.
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
   On the next screen you will see a table displaying all of the test combinations that satisfy
the all-pairs algorithm.  From this screen you can export your results to an XML and/or CSV file.
Any cell containing the string "empty" means that any of that parameters values can be put in the
test combination and all pairs will still be satisfied.
</pre>
<img src="ResultsSample.jpg" alt="Results Sample"><br><br>


<hr>

<pre>
<b>All Pairs Algorithm Description</b>

   The All Pairs algorithm designed for Denumerous works using several steps.  First all the possible 
pairs of parameter values are determined.  Then the list of these pairs is shuffled to provide a 
random order into building the test combinations.  The algorithm then checks each pair to see if it 
can fit into an existing partial test combination.  If it can, it is added to the partial test combination 
and if it can't a new partial test combination is created and the pair is added to it.  Once a partial 
test combination is complete it is turned into a complete test combination.  After all the pairs have 
been added to test combinations any incomplete combinations are filled with "empty" values 
indicating any of the parameter values can be put in and all pairs will still be satisfied.
</pre>

</body>
</html>
