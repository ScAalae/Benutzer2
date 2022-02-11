import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BenutzerController extends HttpServlet {
	private BenutzerDAO benutzerDao;
	
	public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        benutzerDao = new BenutzerDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doGet(request, response);
     }
	
	

	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	                throws ServletException, IOException {

	            String action = request.getServletPath();

	            try {
	                switch (action) {
	                    case "/new":
	                        showNewForm(request, response);
	                        break;
	                    case "/insert":
	                        insertBenutzer(request, response);
	                        break;
	                    case "/delete":
	                        deleteBenutzer(request, response);
	                        break;
	                    case "/edit":
	                        showEditForm(request, response);
	                        break;
	                    case "/update":
	                        updateBenutzer(request, response);
	                        break;
	                    default:
	                       listBenutzer(request, response);
	                        break;
	                }
	            } catch (SQLException ex) {
	                throw new ServletException(ex);
	            }
	        }
            
	 private void listBenutzer(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException, ServletException {

         System.out.println("List Benutzer"); 
          List<Benutzer> listBenutzer = benutzerDao.listAllBenutzer();
         request.setAttribute("listBenutzer", listBenutzer);
         RequestDispatcher dispatcher = request.getRequestDispatcher("BenutzerListe.jsp");
         dispatcher.forward(request, response);
     }
	
	 private void showNewForm(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         RequestDispatcher dispatcher = request.getRequestDispatcher("BenutzerForm.jsp");
         dispatcher.forward(request, response);
     }
	 
	 private void insertBenutzer(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException {
         String vorname = request.getParameter("vorname");
         String nachname = request.getParameter("nachname");
         String email = request.getParameter("email");
         String createdOn = new Date().toString();
      
         Benutzer benutzer = new Benutzer(vorname, nachname, email, createdOn);
         if(vorname!=null&& nachname!=null&&email!=null) {
        	 benutzerDao.insertBenutzer(benutzer);
          
         }
         response.sendRedirect("/Benutzer2/");
     }
	 
	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, ServletException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         Benutzer existingBenutzer = benutzerDao.getBenutzer(id);
         RequestDispatcher dispatcher = request.getRequestDispatcher("BenutzerForm.jsp");
         request.setAttribute("benutzer", existingBenutzer);
         dispatcher.forward(request, response);

     }
	 
	 private void updateBenutzer(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         String vorname = request.getParameter("vorname");
         String nachname = request.getParameter("nachname");
         String email = request.getParameter("email");
         String createdOn = new Date().toString();
     
     Benutzer benutzer = new Benutzer(id, vorname, nachname, email, createdOn);
     benutzerDao.updateBenutzer(benutzer);
     response.sendRedirect("/Benutzer2/");
 }
	 
	 private void deleteBenutzer(HttpServletRequest request, HttpServletResponse response)
             throws SQLException, IOException {
         int id = Integer.parseInt(request.getParameter("id"));

         Benutzer benutzer = new Benutzer(id);
         benutzerDao.deleteBenutzer(benutzer);
         response.sendRedirect("/Benutzer2/");

     }
}
