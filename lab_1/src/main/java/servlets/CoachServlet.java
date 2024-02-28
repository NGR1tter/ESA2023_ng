package servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Coach;
import repositories.CoachRepository;

import java.io.IOException;


@WebServlet("/coach")
public class CoachServlet extends HttpServlet {
    @Inject
    private CoachRepository coachRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String city = request.getParameter("city");

        if (id != null) {
            int id_index = Integer.parseInt(id);
            try {
                coachRepository.delete(id_index);
            } catch (Exception ignored) {

            }
            request.getRequestDispatcher("/").forward(request, response);
        }

        Coach coach = new Coach();
        coach.setName(name);
        coach.setCity(city);

        coachRepository.save(coach);
        request.getRequestDispatcher("/").forward(request, response);
    }

}
