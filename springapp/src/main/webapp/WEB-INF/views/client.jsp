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
    <h3>Loan</h3>
    <table>
    	<tr>
    		<td>Money:  </td>
    		<td>${model.createloan.initialCapital}</td>
    	</tr>
    	<tr>
    		<td>Interest: </td>
    		<td>${model.createloan.interest}</td>
    	</tr>
    	<tr>
    		<td>Payment Period: </td>
    		<td>${model.createloan.paymentPeriod}</td>
    	</tr>
    	<tr>
    		<td>Calculation of Fees: </td>
    		<td>${model.createloan.loanType}</td>
    	</tr>
    	<tr>
    		<td>Number of fees: </td>
    		<td>${model.createloan.amortizationTime}</td>
    	</tr>
    	<tr>
    		<td>Identifier: </td>
    		<td>${model.createloan.id}</td>
    	</tr>
    </table>
  </body>
</html>