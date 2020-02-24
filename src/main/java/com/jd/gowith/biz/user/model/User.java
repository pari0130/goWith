package com.jd.gowith.biz.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jd.gowith.common.util.BaseTimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자의 접근 권한을 protected로 제한
@Getter // lombok getter 자동 생성
@Setter
@Entity // 테이블과 링크될 클래스임을 나타냅니다.
@Table(name = "jd_user", schema = "public")
public class User extends BaseTimeEntity implements UserDetails {
	
    @Id // 해당 테이블의 PK 필드를 나타냅니다.
    @Column(name="user_seq", columnDefinition = "bigint",  unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)// PK의 생성 규칙을 나타냅니다. 기본값은 AUTO 로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 됩니다.
    private Long userPk;

    @Column(name="user_id", columnDefinition = "varchar2",  unique = true, length = 100, nullable = false)
    private String userId;

    @Column(name="user_password", columnDefinition = "varchar2", length = 255, nullable = false)
    private String userPassword;

    @Column(name="user_name", columnDefinition = "varchar2", length = 255, nullable = true)
    private String userName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
