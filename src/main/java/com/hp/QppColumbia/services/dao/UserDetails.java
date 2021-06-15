	package com.hp.QppColumbia.services.dao;
	
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;
	
	@Entity
	@Table(name = "user_details")
	public class UserDetails {
		@Id
		@Column(name = "unique_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private String uniqueId;
	
		@Column(name = "username")
		private String username;
	
		@Column(name = "email")
		private String email;
	
		@Column(name = "role")
		private String role;
	
		public String getUniqueId() {
			return uniqueId;
		}
	
		public void setUniqueId(String uniqueId) {
			this.uniqueId = uniqueId;
		}
	
		public String getUsername() {
			return username;
		}
	
		public void setUsername(String username) {
			this.username = username;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getRole() {
			return role;
		}
	
		public void setRole(String role) {
			this.role = role;
		}
	
	}
