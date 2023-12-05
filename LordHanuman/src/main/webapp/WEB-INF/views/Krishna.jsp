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
            <form action="./saveCitizen" method="post" onsubmit="return validateForm()">
            <h4 style="text-align:center;color:blue;" >Citizen Details Entry Form</h4>
                <div class="form-group">
                    <label class="font-weight-bold">Select Block</label>
                    <select onchange="getPanchayat()" class="form-control" name="blockName" id="blockId">
                        <option value="0">--Select--</option>
                        <c:forEach items="${blockList}" var="block">
                            <option value="${block.blockId}">${block.blockName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label class="font-weight-bold">Select Panchayat</label>
                    <select onchange="getVillage()" class="form-control" name="panchayatName" id="panchayatId">
                        <option value="0">--Select--</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="font-weight-bold">Select Village</label>
                    <select  onchange="" class="form-control" name="villageName" id="villageId">
                        <option value="0">--Select--</option>
                    </select>
                </div>
                
                <!-- <div class="form-group">
                    <label class="font-weight-bold">Select Village</label>
                    <select class="form-control" name="villageName" id="villageId">
                        <option value="0">--Select--</option>
                    </select>
                </div> -->

                <div class="form-group">
                    <label class="font-weight-bold" for="marking">Enter Citizen Name</label>
                    <input type="text" name="citizenName" id="citizenId" class="form-control">
                </div>
                
                <div class="form-group">
                    <label class="font-weight-bold" for="marking">Enter Citizen age</label>
                    <input type="text" name="citizenAge" id="citizenAgeId" class="form-control">
                </div>
                
                <div class="form-group">
                    <label class="font-weight-bold" for="marking">Enter Citizen Annual Income</label>
                    <input type="text" name="citizenAnnualIncome" id="citizenAnnualIncomeId" class="form-control">
                </div>

                <div class="form-group text-center">
                    <button class="btn btn-success" type="submit">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
			<%-- <div class="container">
				<div class="row justify-content-center">
					<div class="col-md-4"
						style="background-color: lightpink; border-radius: 10px;">
						<form action="./getByCountryId" method = "get">
							<div class="form-group">
								<label class="font-weight-bold">Select Country</label> <select
									class="form-control" name="countryName" id="countryId">
									<option value="0">--Select--</option>
									<c:forEach items="${countryList}" var="country">
										<option value="${country.countryId}">${country.countryName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group row justify-content-center">
								<input class="btn btn-success" type="submit" value="show report">
							</div>

						</form>
					</div>
				</div>
			</div> --%>

			<div class="container mt-5">
				<h2>List</h2>

				<table class="table table-striped-light-green">
					<thead>
						<tr>
							<th>SlNO</th>
							<th>Name</th>
							<th>Block Name</th>
							<th>Panchayat Name</th>
							<th>Village Name</th>
							<th>Age</th>
							<th>Annual Income<th>
							<th>Eligibility</th>
							<th>Loan Amount</th>
							<th>Monthly Installment</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${citizenList}" var="mark" varStatus="counter">
							<tr>
								<td>${counter.count}</td>
								<td>${mark.citizenName}</td>
								<td>${mark.block.blockName}</td>
								<td>${mark.panchayat.panchayatName}</td>
								<td>${mark.village.villageName}</td>
								<td>${mark.citizenAge }</td>
								<td>${mark.citizenAnnualIncome}</td>
								<td></td>
								<td><c:choose>
                <c:when test="${mark.citizenAge ge 18 and mark.citizenAge le 60 and mark.citizenAnnualIncome le 50000}">
                    Eligible
                </c:when>
                <c:otherwise>
                    Not Eligible
                </c:otherwise>
            </c:choose></td>
            
            <td>
    <c:choose>
        <c:when test="${mark.citizenAge ge 18 and mark.citizenAge le 60 and mark.citizenAnnualIncome le 50000}">
            <c:choose>
                <c:when test="${mark.citizenAnnualIncome le 100000}">
                    180000
                </c:when>
                <c:when test="${mark.citizenAnnualIncome ge 180001 and mark.citizenAnnualIncome le 300000}">
                    200000
                </c:when>
                <c:when test="${mark.citizenAnnualIncome ge 300001 and mark.citizenAnnualIncome le 400000}">
                    300000
                </c:when>
                <c:when test="${mark.citizenAnnualIncome ge 400001 and mark.citizenAnnualIncome le 500000}">
                    400000
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            0
        </c:otherwise>
    </c:choose>
</td>        
   
					
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-right">
				<a href="download/excel" class="btn btn-warning btn-sm text-right">Download Excel </a>
				</div>
			</div>
</body>

<script type="text/javascript">
function getPanchayat(){

	var block = $('#blockId').val()

	$.ajax({
		url : "./getPanchayatByBlock",
		type : "GET",
		data : {
			blockId : block
		},
		success : function(result) {

			cache: false
			$('#panchayatId').html(result);
		}
	});
}

function getVillage(){
	
	var block = $('#blockId').val()
	var panchayat = $('#panchayatId').val()

	$.ajax({
		url : "./getVillageById",
		type : "GET",
		data : {
			
			blockId : block,
			panchayatId : panchayat
			
		},
		success : function(result) {

			cache: false
			$('#villageId').html(result);
		}
	});
	
}

</script>
</html>
