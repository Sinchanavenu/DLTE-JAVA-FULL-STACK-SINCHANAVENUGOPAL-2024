package Sourceupdate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Create JDBC driver, Data source and bind into JBoss container, then perform following,

Using JNDI data source perform account update in UpdateServlet
 */

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lookup the data source
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
            try (Connection conn = dataSource.getConnection()) {
                String userNameParam = req.getParameter("username");
                String phoneParam = req.getParameter("phone");

                if (userNameParam != null && phoneParam != null) {
                    String username = userNameParam;
                    //Long amount = Long.parseLong(amountParam);
                    Long phone= Long.parseLong(phoneParam);

                    String sql = "UPDATE UserDetails SET phone = ? WHERE username = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setLong(1, phone);
                        stmt.setString(2, username);
                        stmt.executeUpdate();
                    }
                } else {
                    throw new ServletException("Username or phone parameter is null");
                }
            }
        } catch (NamingException | SQLException e) {
            throw new ServletException("Error updating account", e);
        }
    }
}
