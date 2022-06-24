package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    @DisplayName("영화 삽입 테스트")
    public void insertMovieTest() {

        for (int i = 1; i <= 100; i++) {
            Movie movie = Movie.builder()
                    .name("Movie ... " + i)
                    .build();
            System.out.println("----------------------------");
            movieRepository.save(movie);

            int count = (int)(Math.random() * 5) + 1; // 1, 2, 3, 4

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                imageRepository.save(movieImage);
            }
            System.out.println("============================");
        }
    }

    @Test
    @DisplayName("페이지리스트 테스트")
    public void testListPage() {

        // 원래는 PageRequest pageRequest
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageable);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

   @Test
   @DisplayName("특정 영화 조회") // 영화 이미지 개수
   public void testGetMovieWithAll() {

       Long mno = 98L;
       List<Object[]> result = movieRepository.getMovieWithAll(mno);

       System.out.println(result + "\n==========================================");

       for (Object[] objects : result) {
           System.out.println(Arrays.toString(objects));
       }

   }

}