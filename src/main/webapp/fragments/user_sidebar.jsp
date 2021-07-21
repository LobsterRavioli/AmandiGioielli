<nav class="user-sidebar">
	<ul>
		<li>
			<a href="<%=request.getContextPath()%>/user_pages/orders.jsp">
				<i class="fas fa-boxes"></i>
				Ordini
			</a>
		</li>
			<a href="<%=request.getContextPath()%>/user_pages/addresses.jsp">
				<i class="fas fa-map-marked-alt"></i>
				Indirizzi
			</a>
		<li>
			<a href="<%=request.getContextPath()%>/user_pages/account_details.jsp">			
				<i class="fas fa-address-card"></i>
				Informazioni account
			</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/LogOutControl">	
				<i class="fas fa-sign-out-alt"></i>
				Esci
			</a>
		</li>
		
	</ul>
</nav>
