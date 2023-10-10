package kr.co.sboard.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class UserEntity {
	
	@Id
	private String uid;
	private String pass;
	private String name;
    @Column(name = "nick", unique = true)
    private String nick;
    @Column(name="email",unique = true)
    private String email;
    @Column(name = "hp",unique = true)
    private String hp;
    private String role;
    private String zip;
    private String addr1;
    private String addr2;
    private String regIp;
    @CreationTimestamp
    private LocalDateTime regDate;
    private LocalDateTime leaveDate;
	
}
