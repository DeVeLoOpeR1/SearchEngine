<%@page import ="java.util.ArrayList"%>
<%@page import ="Accio.SearchResult"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>What You Want Just Search ?</h1>
  <form action="Search">
     <input type="text" name ="keyword"></input>
     <button type="submit">Search</button>
  </form>
  <form action="History" class="history">
  <button>History</button>
  </form>
  <div class ="search" >
  <table border= 2>
    <tr>
      <td> Name </td>
      <td> Link </td>
    </tr>
   <%
     ArrayList<SearchResult> results = (ArrayList<SearchResult>) request.getAttribute("results");
     for(SearchResult result: results){
   %>
   <tr>
     <td><%out.println(result.getTitle());%></td>
     <td><a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></td>
   </tr>
   <%
    }
   %>
 </table>
 </div>
</body>
</html>
