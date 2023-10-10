package kr.co.sboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Terms")
public class TermsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	private String terms;
	private String privacy;
}
