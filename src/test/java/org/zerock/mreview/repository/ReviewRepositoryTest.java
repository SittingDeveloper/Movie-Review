package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("리뷰 등록 테스트")
    public void insertReviewTest() {

        for (int i = 1; i <= 200; i++) {

            // 영화 번호 1~100
            long mno = (long)(Math.random() * 100 ) + 1;

            // 리뷰어 번호 1~100
            long mid = (long)(Math.random() * 100 ) + 1;

            // 영화, 리뷰어 지정
            Movie movie = Movie.builder().mno(mno).build();
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .grade((int) (Math.random() * 5) + 1)
                    .text(" 이 영화에 대한 느낌 ... " + i)
                    .build();

            reviewRepository.save(review);
        }

    }

    @Test
    @DisplayName("특정 영화 리뷰 조회 테스트")
    public void testGetMovieReviews() {

        Long mno = 100L;
        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        /*for (Review review : result) {
            System.out.println(review);
        }*/

        result.forEach(movieReview -> {
            System.out.println("\t" + movieReview.getReviewnum());
            System.out.println("\t" + movieReview.getGrade());
            System.out.println("\t" + movieReview.getText());
            System.out.println("\t" + movieReview.getMember().getEmail());
        });
    }

    @Transactional
    @Commit
    @Test
    @DisplayName("삭제 테스트")
   public void deleteTest() {

        Long mid = 98L;
        Member member = Member.builder().mid(mid).build();

        reviewRepository.deleteByMember(member);
        memberRepository.delete(member);


    }

}