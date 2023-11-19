<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nueva persona</title>
    <!-- icono de la página -->
    <link rel="icon" href="../images/icono.png" />
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="emp"/>
    </jsp:include>
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Nuevo usuario</h1>
            <hr>
            <form method="POST" action="EmployeeServlet">
                <div class="mb-3">
                    <label class="form-label" for="first_name">First Name</label>
                    <input type="text" class="form-control form-control-sm" id="first_name" name="first_name">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="last_name">Last Name</label>
                    <input type="text" class="form-control form-control-sm" id="last_name" name="last_name">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input type="text" class="form-control form-control-sm" id="email" name="email">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="phone">Phone number</label>
                    <input type="text" class="form-control form-control-sm" id="phone" name="phone">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="hire_date">Hire date</label>
                    <input type="text" class="form-control form-control-sm" id="hire_date" name="hire_date">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="job_id">Job ID</label>
                    <select name="job_id" id="job_id" class="form-select">
                        <% for (Job job : listaTrabajos) {%>
                        <option value="<%=job.getJobId()%>"><%=job.getJobTitle()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="salary">Salary</label>
                    <input type="text" class="form-control form-control-sm" id="salary" name="salary">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="commission">Commision PCT</label>
                    <input type="text" class="form-control form-control-sm" id="commission" name="commission">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="manager_id">Manager</label>
                    <select name="manager_id" id="manager_id" class="form-select">
                        <option value="sin-jefe">--Sin jefe--</option>
                        <% for (Employee employee : listaJefes) {%>
                        <option value="<%=employee.getEmployeeId()%>"><%=employee.getFirstName()%> <%=employee.getLastName()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="department_id">Department</label>
                    <select name="department_id" id="department_id" class="form-select">
                        <% for (Department department : listaDepartamentos) {%>
                        <option value="<%=department.getDepartmentId()%>"><%=department.getDepartmentName()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
</body>
</html>
