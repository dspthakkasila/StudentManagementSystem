package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAOImpl;
import model.Student;

@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet
extends HttpServlet {

    private static final long
    serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        StudentDAOImpl dao =
                new StudentDAOImpl();

        // PAGINATION

        int page = 1;

        int recordsPerPage = 5;

        if(request.getParameter("page")
                != null) {

            page = Integer.parseInt(
                    request.getParameter(
                            "page"));
        }

        int start =
                (page - 1)
                * recordsPerPage;

        // SEARCH

        String search =
                request.getParameter(
                        "search");

        // FILTER

        String course =
                request.getParameter(
                        "course");

        // SORT

        String sort =
                request.getParameter(
                        "sort");

        // FETCH DATA

        List<Student> list =

                dao.getStudentsWithPagination(

                        start,

                        recordsPerPage,

                        search,

                        course,

                        sort);

        // TOTAL COUNT

        int totalStudents =

                dao.getTotalStudentCount(

                        search,

                        course);

        int totalPages =

                (int)Math.ceil(

                        totalStudents * 1.0

                        / recordsPerPage);

        // DASHBOARD

        double avgMarks =
                dao.getAverageMarks();

        double highestMarks =
                dao.getHighestMarks();

        // ATTRIBUTES

        request.setAttribute(
                "students",
                list);

        request.setAttribute(
                "currentPage",
                page);

        request.setAttribute(
                "totalPages",
                totalPages);

        request.setAttribute(
                "totalStudents",
                totalStudents);

        request.setAttribute(
                "avgMarks",
                avgMarks);

        request.setAttribute(
                "highestMarks",
                highestMarks);

        request.setAttribute(
                "search",
                search);

        request.setAttribute(
                "course",
                course);

        request.setAttribute(
                "sort",
                sort);

        request.getRequestDispatcher(
                "viewStudents.jsp")
                .forward(
                        request,
                        response);
    }
}