package com.jd.gowith.biz.user.model;

import com.jd.gowith.common.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자의 접근 권한을 protected로 제한
@Getter // lombok getter 자동 생성
@Setter
@Entity // 테이블과 링크될 클래스임을 나타냅니다.
@Table(name = "jpc_posts", schema = "public")
public class User extends BaseTimeEntity{
	
    @Id // 해당 테이블의 PK 필드를 나타냅니다.
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)// PK의 생성 규칙을 나타냅니다. 기본값은 AUTO 로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 됩니다.
    private Long userId;

    @Column(name="login_id", columnDefinition = "varchar", length = 20, nullable = false)
    private String loginId;

    @Column(name="password", columnDefinition = "varchar", length = 255, nullable = false)
    private String password;

    @Column(name="user_name", columnDefinition = "varchar", length = 255, nullable = true)
    private String userName;

    @Column(name = "event_date", updatable = true)
    private LocalDateTime eventDate;
}
