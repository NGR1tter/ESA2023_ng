package servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Coach;
import models.Pokemon;
import repositories.CoachRepository;
import repositories.PokemonRepository;

import java.io.IOException;

@WebServlet("/pokemon")
public class PokemonServlet extends HttpServlet {
    @Inject
    private PokemonRepository pokemonRepository;
    @Inject
    private CoachRepository coachRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coachId = request.getParameter("coachId");
        Coach coach = coachRepository.findById(Integer.parseInt(coachId));
        String name = request.getParameter("name");
        String life = request.getParameter("life");
        String attack = request.getParameter("attack");

        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setAttack(Integer.parseInt(attack));
        pokemon.setLife(Integer.parseInt(life));
        pokemon.setCoach(coach);
        coach.addPokemon(pokemon);

        pokemonRepository.save(pokemon);
        request.getRequestDispatcher("/").forward(request, response);
    }
}
