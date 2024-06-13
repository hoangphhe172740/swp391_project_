/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Course;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    private boolean isCheck(int d, int[] id) {
        if (id == null) {
            return false;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == d) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cid_raw = request.getParameter("cid");
        String[] cidd_raw = request.getParameterValues("cidd");
        int cid = 0;
        DAO d = new DAO();
        List<Course> Courses = new ArrayList<>();
        if (cid_raw != null) {
            System.out.println(cid_raw);
            cid = Integer.parseInt(cid_raw);
            Courses = d.getCourseByCid(cid);
        }
        List<Course> listcourse = d.getAllCourse();
        List<Category> listCate = d.getAllCaregories();
        boolean[] chid = new boolean[listCate.size() + 1];
        int[] cidd = null;
        if (cid_raw != null) {
            cid = Integer.parseInt(cid_raw);
            Courses = d.getCourseByCid(cid);
            if (cid == 0) {
                chid[0] = true;
            }
        }
        if (cidd_raw != null) {
            cidd = new int[cidd_raw.length];
            for (int i = 0; i < cidd.length; i++) {
                cidd[i] = Integer.parseInt(cidd_raw[i]);
            }
            Courses = d.searchByCheck(cidd);
        }
        if (cid_raw == null) {
            chid[0] = true;
        }
        if ((cidd_raw != null) && (cidd[0] != 0)) {
            chid[0] = false;
            for (int i = 1; i < chid.length; i++) {
                if (isCheck(listCate.get(i - 1).getCid(), cidd)) {
                    chid[i] = true;
                } else {
                    chid[i] = false;
                }
            }
        }
        Course pnew = d.getNewCourse();
        int paging;
        String paging_raw = request.getParameter("page");
        if(paging_raw == null){
            paging = 1;
        }else{
            paging = Integer.parseInt(paging_raw);
        }
        System.out.println(Courses);
        List<Course> list = null;
        List<Course> listPage = list.subList(6 * (paging - 1), (paging * 6) < list.size() ? paging * 6 : list.size());      
        request.setAttribute("chid", chid);
        request.setAttribute("cidd", cidd);
        request.setAttribute("page", paging);
        request.setAttribute("listC", listCate);
        request.setAttribute("pnew", pnew);
        request.setAttribute("listP", listPage);
        request.setAttribute("listcourses", listcourse);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cid_raw = request.getParameter("cid");
        String[] cidd_raw = request.getParameterValues("cidd");
        int cid = 0;
        List<Course> list = null;
        DAO d = new DAO();
        List<Course> Courses = new ArrayList<>();
        List<Course> listcourse = d.getAllCourse();
        List<Category> listCate = d.getAllCaregories();
        Course pnew = d.getNewCourse();
        if (cid_raw != null) {
            System.out.println(cid_raw);
            cid = Integer.parseInt(cid_raw);
            Courses = d.getCourseByCid(cid);
        }
        boolean[] chid = new boolean[listCate.size() + 1];
        int[] cidd = null;
        if (cid_raw != null) {
            cid = Integer.parseInt(cid_raw);
            Courses = d.getCourseByCid(cid);
            if (cid == 0) {
                chid[0] = true;
            }
        }
        if (cidd_raw != null) {
            cidd = new int[cidd_raw.length];
            for (int i = 0; i < cidd.length; i++) {
                cidd[i] = Integer.parseInt(cidd_raw[i]);
            }
            Courses = d.searchByCheck(cidd);
        }
        if (cid_raw == null) {
            chid[0] = true;
        }
        if ((cidd_raw != null) && (cidd[0] != 0)) {
            chid[0] = false;
            for (int i = 1; i < chid.length; i++) {
                if (isCheck(listCate.get(i - 1).getCid(), cidd)) {
                    chid[i] = true;
                } else {
                    chid[i] = false;
                }
            }
        }
        request.setAttribute("cid", cid);
        request.setAttribute("pnew", pnew);
        request.setAttribute("chid", chid);
        request.setAttribute("listcourses", listcourse);
        request.setAttribute("listC", listCate);
        request.setAttribute("listcourses", list);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
