import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(description="Login Servlet Testing",
        urlPatterns={ "/LoginServlet" })
public class LoginServlet extends HttpServlet {
    PatternChecker patternChecker=new PatternChecker();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user=request.getParameter("user");
        String pwd=request.getParameter("pwd");
        if(patternChecker.checkUserName(user) && patternChecker.checkPassword(pwd)){
            request.setAttribute("user",user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request,response);
        }
            else{
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out=response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request,response);
        }
    }
}
class PatternChecker{
    private final static String NAME_CHECKER="^[A-Z]{1}[a-z]{2,}$";
    private final static String PASSWORD_PATTERN4= "(?=.*[0-10])(?=[^@|#|$|&|%]*[@|&|$|#|%][^@|#|$|&|%]*$)(?=.*[a-z])(?=.*[A-Z])[A-Za-z0-9@#$%]{8,20}";
    public boolean checkUserName(String userName){
        if(userName.matches(NAME_CHECKER))
            return true;
        return false;
    }
    public boolean checkPassword(String givenPassword){
        if(givenPassword.matches(PASSWORD_PATTERN4))
            return true;
        return false;
    }
}
