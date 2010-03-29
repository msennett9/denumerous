<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- 
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
 -->
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <h2 align="center">Test Combinations:</h2>
  <table border="1" align="center">
  <tr bgcolor="#9acd32">
   <th align="center">ID</th>
   <xsl:apply-templates select="TestCombinations/InputParameters/ParameterName"/>
  </tr>
  <xsl:apply-templates select="TestCombinations/TestCombination"/>
  </table>
</xsl:template>

<xsl:template match="ParameterName">
   <th align="center"><xsl:value-of select="@value"/></th>
</xsl:template>

<xsl:template match="TestCombination">
   <TR>
      <td align="center"><xsl:value-of select="@id"/></td>
      <xsl:apply-templates select="Parameter" />
   </TR>
</xsl:template>

<xsl:template match="Parameter">
      <td align="center"><xsl:value-of select="@value"/></td>
</xsl:template>

</xsl:transform>