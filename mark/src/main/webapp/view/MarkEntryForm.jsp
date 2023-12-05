<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>HomePage</title>
<!-- Include Bootstrap CSS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>
<style>
body {
	font-family: cursive;
}

.table-striped-light-green tbody tr:nth-child(odd) {
    background-color: lightgreen;
}
</style>
<body>

	<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5" style="background-color: lightblue; border-radius: 10px; padding: 20px;">
            <form action="./saveMark" method="post" onsubmit="return validateForm()">
            <h4 style="text-align:center;color:blue;" >Mark Entry Form</h4>
                <div class="form-group">
                    <label class="font-weight-bold">Select Batch</label>
                    <select onchange="getTechnology()" class="form-control" name="batchName" id="batchId">
                        <option value="0">--Select--</option>
                        <c:forEach items="${batchList}" var="batch">
                            <option value="${batch.batchId}">${batch.batchName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="font-weight-bold">Select Technology</label>
                    <select onchange="openDropdown()" class="form-control" name="technologyName" id="technologyId">
                        <option value="0">--Select--</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="font-weight-bold">Select Employee</label>
                    <select class="form-control" name="employeeName" id="employeeId">
                        <option value="0">--Select--</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="font-weight-bold" for="marking">Enter Mark</label>
                    <input type="number" name="mark" id="markId" class="form-control">
                </div>

                <div class="form-group text-center">
                    <button class="btn btn-success" type="submit">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-4"
						style="background-color: lightpink; border-radius: 10px;">
						<form action="./getList" method="POST">
							<div class="form-group">
								<label class="font-weight-bold">Select Batch</label> <select
									class="form-control" name="batchName" id="batchId">
									<option value="0">--Select--</option>
									<c:forEach items="${batchList}" var="batch">
										<option value="${batch.batchId}">${batch.batchName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group row justify-content-center">
								<input class="btn btn-success" type="submit" value="show report">
							</div>

						</form>
					</div>
				</div>
			</div>

			<div class="container mt-5">
				<h2>List</h2>

				<table class="table table-striped-light-green">
					<thead>
						<tr>
							<th>SlNO</th>
							<th>Batch Name</th>
							<th>Batch Start Date</th>
							<th>Technology Name</th>
							<th>Employee Name</th>
							<th>Employee Phone</th>
							<th>Mark</th>
							<th>Grade</th>
							<th>Status</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${RESULT}" var="mark" varStatus="counter">
							<tr>
								<td>${counter.count}</td>
								<td>${mark[0]}</td>
								<td>${mark[1]}</td>
								<td>${mark[2]}</td>
								<td>${mark[3]}</td>
								<td>${mark[4]}</td>
								<td>${mark[5]}</td>
								<td>${mark[6]}</td>
								<td>${mark[7]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>

</body>

<script type="text/javascript">
	function getTechnology() {

		var batch = $('#batchId').val();

		$.ajax({
			url : "./getTechByBatchId",
			type : "GET",
			data : {
				batchId : batch
			},
			success : function(result) {

				cache: false
				$('#technologyId').html(result);
			}
		});
	}

	function openDropdown() {
		var batch = $('#batchId').val();
		var technology = $('#technologyId').val();

		$.ajax({
			url : "./getEmployeeById",
			type : "GET",
			data : {
				batchId : batch,
				technologyId : technology
			},
			success : function(result) {

				cache: false
				$('#employeeId').html(result);
			}
		});
	}


	function validateForm(){
		
		var batchId = $('#batchId').val();
		var technologyId = $('#technologyId').val();
		var employeeId = $('#employeeId').val();
		var markId = $('#markId').val();

		if(batchId == '0'){
			alert("Batchname must be choosed");
		return false;
		}
		if(technologyId == '0'){
			alert("Technology must be choosed");
		return false;
		}
		if(employeeId == '0'){
			alert("EmployeeNamezz must be choosed");
		return false;
	}
		if(markId == ""){
			alert("Mark must be there");
		return false;
	}
		
	return true;
	}	
</script>
</html>