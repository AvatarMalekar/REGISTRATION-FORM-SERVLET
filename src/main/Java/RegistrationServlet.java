import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(description="Registration Servlet Testing",
        urlPatterns={ "/RegistrationServlet" })
public class RegistrationServlet extends HttpServlet {
    PatternChecker patternChecker=new PatternChecker();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String pwd=request.getParameter("pwd");
        String mailId=request.getParameter("mailId");
        String dob=request.getParameter("dob");

        if(patternChecker.checkUserName(userName) && patternChecker.checkPassword(pwd)){
            request.setAttribute("userName",userName);
            request.getRequestDispatcher("RegistrationSuccess.jsp").forward(request,response);
        } else{
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Registration.html");
            PrintWriter out=response.getWriter();
            out.println("<font color=red>First name of user name must be capital" +
                    " And Password Should be of eight digit having one numeric,alphabet and exactly one special character</font>");
            rd.include(request,response);
        }

    }
}
