<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Coach" %>
<%@ page import="models.Pokemon" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<body>
<div class="container" align="center" valign="middle">
    <div class="coach-input">
        You can create a coach
    </div>
    <div class="createCoach">
        <div class="container-input">
            <form action="./coach" method="post">
                <input type="text" name="name" , placeholder="Coachs name" , required="required">
                <input type="text" name="city" , placeholder="City" , required="required">
                <button type="submit">Save</button>
            </form>
        </div>
    </div>
    <div class="coaches">
        <% List<Coach> coaches = (List<Coach>) request.getAttribute("table_coaches");%>
        <div class="table-coach">
            <table>
                <tr>
                    <th>Coach ID</th>
                    <th>Coach Name</th>
                    <th>Pokemon names</th>
                </tr>
                <% for (Coach coach : coaches) { %>
                <div class="containerCoach">
                    <tr>
                        <th><%= coach.getId() %>
                        </th>
                        <th>
                            <%= coach.getName() %>
                        </th>
                        <th>
                            <% for (Pokemon pokemon : coach.getPokemons()) { %>
                            <%= pokemon.getName() %>
                            <% } %>
                        </th>
                    </tr>
                </div>
                <% } %>
            </table>
            <br>
            <form action="./pokemon" method="post">
                <label>Fill items to the Pokemons </label>
                <input type="number" name="coachId" placeholder="Coach ID" required="required">
                <input type="text" name="name" placeholder="name" required="required">
                <input type="number" name="attack" placeholder="attack" required="required">
                <input type="number" name="life" placeholder="life" required="required">
                <button type="submit">Save</button>
            </form>
            </br>
        </div>
    </div>
    <% if (!coaches.isEmpty()) { %>
    <div class="container-delete">
        <div class="coach-input">
            select id coach to delete
            <form action="./coach" method="post">
                <input type="number" name="id" , placeholder="Id" , required="required">
                <button type="submit">Delete</button>
            </form>
        </div>
    </div>
    <% } %>
</div>
</body>
</head>
</html>
