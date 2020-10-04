package com.busyvacation.aboard.db.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.busyvacation.aboard.db.dto.Post;
import com.busyvacation.aboard.db.dto.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomPostRepositoryImpl implements CustomPostRepository {

    @PersistenceContext
    private EntityManager em;

    private QPost post = QPost.post;

    @Override
    public List<Post> get1() {

        JPAQueryFactory query = new JPAQueryFactory(em); // 실제로 쿼리 되는 문장?

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // 여기다가 조건절을 단다.

        List<Post> posts = query.select(post).from(post)

                // .offset(pageable.getOffset())
                // .limit(pageable.getPageSize())

                .where(booleanBuilder).fetch(); // fetch 반환값이 list다

        return posts;
    }
    

    @Override
    public List<Post> get2() {
        
        // String sql = "select  p.postid,p.title, p.memberid, p.attach,  p.created_date, p.updated_date from post p";
        String sql = "select  * from post ";

        Query nativeQuery  = em.createNativeQuery(sql);

        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        List<Post>  result = jpaResultMapper.list(nativeQuery, Post.class);

    



        return result;
    }

    //     Query nativeQuery  = em.createNativeQuery(sql);
    //     // .setParameter("mId", managerId);
    //     // .getResultList();
    //     JpaResultMapper jpaResultMapper = new JpaResultMapper();
    //     List<CustomOrderState>  result = jpaResultMapper.list(nativeQuery, CustomOrderState.class);


    
}