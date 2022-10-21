package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.UserVoteState;
import com.ndp.knowsharing.Repositories.UserVoteStateRepo;

@Service
public class UserVoteStateService {
    @Autowired
    private UserVoteStateRepo repo;

    public List<UserVoteState> retrieveAll() {
        return repo.findAll();
    }

    public UserVoteState retrieveById(String id) {
        UserVoteState sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public List<UserVoteState> retrieveByArticleId(String articleId) {
        return repo.findByArticleId(articleId);
    }

    public UserVoteState retrieveOneByArticleIdAndAuthor(String articleId, String userId) {
        UserVoteState sth = null;

        try {
            sth = repo.findByArticleIdAndAuthor(articleId, userId);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public UserVoteState createOne(UserVoteState userVoteState) {
        UserVoteState tmp = null;

        userVoteState.setId(null);

        try {
            tmp = repo.save(userVoteState);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public UserVoteState updateOne(UserVoteState userVoteState) {
        UserVoteState tmp = null;

        try {
            repo.findById(userVoteState.getId()).get();

            tmp = repo.save(userVoteState);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public UserVoteState saveOne(UserVoteState userVoteState) {
        UserVoteState tmp = null;

        try {
            tmp = repo.save(userVoteState);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Boolean deleteAllByArticleId(String articleId) {
        Boolean isSuccess = false;

        try {
            Long ii = repo.deleteByArticleId(articleId);

            if(ii > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }
}
