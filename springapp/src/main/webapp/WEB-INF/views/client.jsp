<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <h3>Client</h3>
    <br>
    <table>
    	<tr>
    		<td>Name: </td>
    		<td>${model.client.name}</td>
    	</tr>
    	<tr>
    		<td>Surname: </td>
    		<td>${model.client.surname}</td>
    	</tr>
    	<tr>
    		<td>NIF: </td>
    		<td>${model.client.id}</td>
    	</tr>
    </table>
    <a href="<c:url value="createloan.htm"/>">Create New Loan</a>
    <br>
    <h3>Loans</h3>
   	<c:forEach items="${model.loans}" var="loan"><br>
   	 <table>
    	<tr>
    		<td>Identifier: </td>
    		<td>${loan.idLoan}</td>
    	</tr>
    	<tr>
    		<td>Money:  </td>
    		<td>${loan.amountOfMoney}</td>
    	</tr>
    	<tr>
    		<td>Interest: </td>
    		<td>${loan.interest}</td>
    	</tr>
    	<tr>
    		<td>Payment Period: </td>
    		<td>${loan.paymentPeriodString}</td>
    	</tr>
    	<tr>
    		<td>Number of fees: </td>
    		<td>${loan.amortizationTime}</td>
    	</tr>
    	
    </table>
   	</c:forEach>
  </body>
</html>