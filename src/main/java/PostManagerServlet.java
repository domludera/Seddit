import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PostManager", urlPatterns = "/app/home")
@javax.servlet.annotation.MultipartConfig
public class PostManagerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager db = new DBManager();
        db.getConnection();

        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");

        Part filePart = req.getPart("file");


        //sending message to DB with title, content, posterID, and current system time
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String visibility = req.getParameter("visibility");
        db.postMessage(title, content, username, filePart, visibility);
        resp.sendRedirect("/app/home");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        DBManager db = new DBManager();
        db.getConnection();
        int viewCount = 10;

        if (req.getParameter("viewposts") != null) {
            try {
                viewCount = Integer.parseInt(req.getParameter("viewposts"));
            } catch (NumberFormatException e) {
                viewCount = -1;
            }
        }

        //Populate with UserPosts
        req.setAttribute("UserPosts", db.getUserPosts(viewCount, (String) session.getAttribute("visibility")));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/app/home.jsp");
        rd.forward(req, resp);
    }
}
