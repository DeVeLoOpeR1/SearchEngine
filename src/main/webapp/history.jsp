<%@page import ="java.util.ArrayList"%>
<%@page import ="Accio.HistoryResult"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>I Know Your History</h1>
<div class ="search" >

  <table border= 2>
    <tr>
      <td> Name </td>
      <td> Link </td>
    </tr>
   <%
     ArrayList<HistoryResult> results = (ArrayList<HistoryResult>) request.getAttribute("results");
     for(HistoryResult result: results){
   %>
   <tr>
     <td><a href="<%out.println(result.getName());%>"><%out.println(result.getName());%></td>
     <td><a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></td>
   </tr>
   <%
    }
   %>
 </table>
 </div>
</body>
</html>
