package com.cris.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Changed to IDENTITY
	Long id;

	@Column(unique = true) // Optional: Ensures emails are unique in DB
	String email;
	String password;
}