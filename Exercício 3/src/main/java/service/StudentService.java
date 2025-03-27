// Desenvolvido por Daniella Silva - Ciência da Computação - PUC Minas - 2025

package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.util.List;
import dao.StudentDAO;
import model.Student;
import spark.Request;
import spark.Response;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_NAME = 2;
    private final int FORM_ORDERBY_COURSE = 3;

    public StudentService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(FORM_INSERT, new Student(), FORM_ORDERBY_NAME);
    }

    public void makeForm(int orderBy) {
        makeForm(FORM_INSERT, new Student(), orderBy);
    }

    public void makeForm(int type, Student estudante, int orderBy) {
        String fileName = "form.html";
        form = "";

        try {
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNext()) {
                form += (input.nextLine() + "\n");
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Object insert(Request request, Response response) {
        String nome = request.queryParams("name");
        String email = request.queryParams("email");
        String telefone = request.queryParams("phoneNumber");
        LocalDate dataMatricula = LocalDate.parse(request.queryParams("enrollmentDate"));
        String curso = request.queryParams("course");

        String resp = "";

        Student estudante = new Student(-1, nome, email, telefone, dataMatricula, curso);

        if (studentDAO.inserir(estudante)) {
            resp = "Aluno (" + nome + ") adicionado com sucesso!";
            response.status(201);
        } else {
            resp = "Aluno (" + nome + ") não pôde ser adicionado!";
            response.status(404);
        }

        makeForm();
        form = form.replaceFirst("value=\"\"", "value=\"" + resp + "\"");

        return form;
    }

    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Student estudante = studentDAO.obter(id);

        if (estudante != null) {
            response.status(200);
            makeForm(FORM_DETAIL, estudante, FORM_ORDERBY_NAME);
        } else {
            response.status(404);
            String resp = "Aluno " + id + " não encontrado.";
            makeForm();
            form = form.replaceFirst("<RESULT-OP>", resp);
        }

        return form;
    }

    public Object getToUpdate(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Student estudante = studentDAO.obter(id);

        if (estudante != null) {
            response.status(200);
            makeForm(FORM_UPDATE, estudante, FORM_ORDERBY_NAME);
        } else {
            response.status(404);
            String resp = "Aluno " + id + " não encontrado.";
            makeForm();
            form = form.replaceFirst("<RESULT-OP>", resp);
        }

        return form;
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Student estudante = studentDAO.obter(id);

        String resp = "";

        if (estudante != null) {
            estudante.setNome(request.queryParams("name"));
            estudante.setEmail(request.queryParams("email"));
            estudante.setTelefone(request.queryParams("phoneNumber"));
            estudante.setDataMatricula(LocalDate.parse(request.queryParams("enrollmentDate")));
            estudante.setCurso(request.queryParams("course"));

            studentDAO.atualizar(estudante);
            response.status(200);
            resp = "Aluno (ID " + estudante.getId() + ") atualizado!";
        } else {
            response.status(404);
            resp = "Aluno (ID " + id + ") não pôde ser atualizado!";
        }

        makeForm();
        form = form.replaceFirst("value=\"\"", "value=\"" + resp + "\"");

        return form;
    }

    public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Student estudante = studentDAO.obter(id);

        String resp = "";

        if (estudante != null) {
            studentDAO.deletar(id);
            response.status(200);
            resp = "Aluno (" + id + ") excluído!";
        } else {
            response.status(404);
            resp = "Aluno (" + id + ") não pôde ser excluído!";
        }

        makeForm();
        form = form.replaceFirst("value=\"\"", "value=\"" + resp + "\"");

        return form;
    }

    public Object getAll(Request request, Response response) {
        int orderBy = Integer.parseInt(request.params(":orderby"));

        makeForm(orderBy);

        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");

        return form;
    }
}
