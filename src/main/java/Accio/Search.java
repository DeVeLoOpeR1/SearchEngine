package Accio;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet ("/Search")
    public class Search extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) {
            try {
                String keyword = request.getParameter("keyword");
                Connection connection = DatabaseConnection.getConnection();
                keyword = keyword.toLowerCase();
                // execute sql to get data related to the keyword from the database
                ResultSet resultSet = connection.createStatement().executeQuery("select pagetitle,pagelink,(length(lower(pagedata))-length(replace(lower(pagedata),'"+keyword+"',' ')))/length('"+keyword+"') as countoccurance from pages order by countoccurance desc limit 30;");

                ArrayList<SearchResult> results = new ArrayList<SearchResult>();

                while(resultSet.next()){

                    SearchResult searchResult = new SearchResult();
                    searchResult.setTitle(resultSet.getString("pagetitle"));

                    searchResult.setLink(resultSet.getString("pagelink"));
                    results.add(searchResult);
                }


                PreparedStatement preparedStatement = connection.prepareStatement("insert into history values (?,?)");
                preparedStatement.setString(1,keyword);
                preparedStatement.setString(2,"http://localhost:8080/SmartSearchEngine/Search?keyword="+keyword);
                preparedStatement.executeUpdate(); // allready prepared some statement to perform some manipulation on database
                 // set attribute results and make a request to the server to give the result back to user

                request.setAttribute("results",results);
                request.getRequestDispatcher("search.jsp").forward(request,response);


                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<h3>This is my servlet " + keyword + " </h3>");
                //Crawler crawler = new Crawler();
                //crawler.getPageTextAndLinks("https://www.javatpoint.com",0);
            } catch (IOException ioException) {
                System.out.println(ioException);
            }
            catch (SQLException sqlException)   //exception handling for the database related things
            {
                sqlException.printStackTrace();
            } catch (ServletException e)
            {
                e.printStackTrace();
            }
        }

    }

