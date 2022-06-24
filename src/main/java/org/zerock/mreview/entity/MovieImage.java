package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie") // 연관 관계 주의
public class MovieImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid; // java.util.UUID 를 이용해 고유한 번호를 생성해서 사용

    private String imgName;

    private String path; // 년/월/일 폴더 구조

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie; // Movie : PK  , MovieImage : FK
}
