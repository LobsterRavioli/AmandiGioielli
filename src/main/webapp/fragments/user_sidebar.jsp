<nav class="user-sidebar">
	<ul>
		<li>
			<a href="<%=request.getContextPath()%>/user_pages/orders.jsp">
				<i class="fas fa-boxes" style="color: #00ddc9;  margin-right: 10px;"></i>
				Ordini
			</a>
		</li>
			<a href="<%=request.getContextPath()%>/user_pages/addresses.jsp">
				<i class="fas fa-map-marked-alt" style="color: #00ddc9; margin-right: 10px;"></i>
				Indirizzi
			</a>
		<li>
			<a href="<%=request.getContextPath()%>/user_pages/account_details.jsp">			
				<i class="fas fa-address-card" style="color: #00ddc9; margin-right: 10px;"></i>
				Informazioni account
			</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/LogOutControl">	
				<i class="fas fa-sign-out-alt" style="color: #00ddc9; margin-right: 10px;"></i>
				Esci
			</a>
		</li>
		
	</ul>
</nav>
