package com.ndp.knowsharing.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.Article;
import com.ndp.knowsharing.Entities.ArticleTagRel;
import com.ndp.knowsharing.Entities.Comment;
import com.ndp.knowsharing.Entities.UserVoteState;
import com.ndp.knowsharing.Models.Article.ArticleCreateModel;
import com.ndp.knowsharing.Models.Article.ArticleItemReturnModel;
import com.ndp.knowsharing.Models.Article.ArticleUpdateModel;
import com.ndp.knowsharing.Models.Article.PageOfArticleModel;
import com.ndp.knowsharing.Models.Comment.CommentCreateModel;
import com.ndp.knowsharing.Models.UserVoteState.UVSReturnModel;
import com.ndp.knowsharing.Models.UserVoteState.UVSUpdateModel;
import com.ndp.knowsharing.Services.ArticleService;
import com.ndp.knowsharing.Services.ArticleTagRelService;
import com.ndp.knowsharing.Services.CommentService;
import com.ndp.knowsharing.Services.UserVoteStateService;
import com.ndp.knowsharing.Utils.UriParser.MyUriParserUtils;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private MyUriParserUtils myUriParserUtils;

    @Autowired
    private ArticleTagRelService articleTagRelService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserVoteStateService userVoteStateService;

    /*
     * Retrieve Category
     */
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll(@RequestParam(value = "page", required = true) Integer pageNum, @RequestParam(value = "category", required = false) String categoryName, @RequestParam(value = "hidden", required = false) Boolean hidden, @RequestParam(value = "tagids", required = false) List<String> tagIds) {
        ResponseEntity<Object> entity;

        PageOfArticleModel pageOfArticleModel = new PageOfArticleModel();

        if(categoryName == null) {
            if(hidden == null) {
                if(tagIds == null) {
                    List<Article> articles = articleService.retrieveOneCommonPage(pageNum);

                    Integer noPage = (int)Math.ceil(Double.valueOf(articleService.retrieveNumOfPages(categoryName).intValue()) / 10); // ceiling number of pages and convert to Integer

                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                } else {
                    List<Article> articles = articleService.retrieveOneCommonPageWithTags(tagIds, pageNum);

                    Integer noPage = articleService.retrieveNumOfPagesAllWithTagIds(tagIds).intValue(); // ceiling number of pages and convert to Integer

                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                }

            } else {
                if(tagIds == null) {
                    List<Article> articles = articleService.retrieveOneCommonPageAndHidden(pageNum, hidden ? 1 : 0);

                    Integer noPage = (int)Math.ceil(Double.valueOf(articleService.retrieveNumOfPagesAndHidden(categoryName, hidden ? 1 : 0).intValue()) / 10);

                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                } else {
                    List<Article> articles = articleService.retrieveOneCommonPageAndHiddenWithTagIds(tagIds, pageNum, hidden ? 1 : 0);

                    Integer noPage = articleService.retrieveNumOfPagesByHiddenWithTagIds(tagIds, hidden ? 1 : 0).intValue();

                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                }
            }
        } else {
            if(hidden == null) {
                if(tagIds == null) {
                    List<Article> articles = articleService.retrieveOnePageByCategory(pageNum, categoryName);

                    Integer noPage = (int)Math.ceil(Double.valueOf(articleService.retrieveNumOfPages(categoryName).intValue()) / 10);

                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                } else {
                    List<Article> articles = articleService.retrieveOnePageByCategoryWithTagIds(tagIds, pageNum, categoryName);

                    Integer noPage = articleService.retrieveNumOfPagesByCategoryWithTagIds(tagIds, categoryName).intValue();

                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                }
            } else {
                if(tagIds == null) {
                    List<Article> articles = articleService.retrieveOnePageByCategoryAndHidden(pageNum, categoryName, hidden ? 1 : 0);

                    Integer noPage = (int)Math.ceil(Double.valueOf(articleService.retrieveNumOfPagesAndHidden(categoryName, hidden ? 1 : 0).intValue()) / 10);
                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                } else {
                    List<Article> articles = articleService.retrieveOnePageByCategoryAndHiddenWithTagIds(tagIds, pageNum, categoryName, hidden ? 1 : 0);

                    Integer noPage = articleService.retrieveNumOfPagesByCategoryAndHiddenWithTagIds(tagIds, categoryName, hidden ? 1 : 0).intValue();
                    List<ArticleItemReturnModel> articleItemReturnModels = new ArrayList<ArticleItemReturnModel>();
                    for(Article articleItem : articles) {
                        List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articleItem.getId());

                        Integer voteScore = 0;

                        for(UserVoteState uvsItem : userVoteStates) {
                            voteScore = voteScore + uvsItem.getVoteState();
                        }

                        ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articleItem, voteScore);

                        articleItemReturnModels.add(articleItemReturnModel);
                    }

                    pageOfArticleModel.setNumberOfPages(noPage);
                    pageOfArticleModel.setCurrentPage(pageNum);
                    pageOfArticleModel.setArticles(articleItemReturnModels);

                    entity = new ResponseEntity<>(pageOfArticleModel, HttpStatus.OK);
                }
            }
        }

        return entity;
    }
    // public ResponseEntity<Object> retrieveAll() {
    //     ResponseEntity<Object> entity;

    //     List<Article> articles = articleService.retrieveAll();

    //     entity = new ResponseEntity<>(articles, HttpStatus.OK);

    //     return entity;
    // }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveById(@PathVariable("id") String id) {
        ResponseEntity<Object> entity;

        Article article = articleService.retrieveOne(id);

        entity = new ResponseEntity<>(article, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/by-url/{url}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveByUrl(@PathVariable("url") String url) {
        ResponseEntity<Object> entity;

        List<Article> articles = articleService.retrieveByUrl(url);

        if(articles.size() > 0) {
            List<UserVoteState> userVoteStates = userVoteStateService.retrieveByArticleId(articles.get(0).getId());

            Integer voteScore = 0;

            for(UserVoteState uvsItem : userVoteStates) {
                voteScore = voteScore + uvsItem.getVoteState();
            }

            ArticleItemReturnModel articleItemReturnModel = new ArticleItemReturnModel(articles.get(0), voteScore);

            entity = new ResponseEntity<>(articleItemReturnModel, HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not found\" }", HttpStatus.NOT_FOUND);
        }

        return entity;
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createOneArticle(@RequestBody ArticleCreateModel articleCreateModel) {
        ResponseEntity<Object> entity;

        if(articleCreateModel.getCreatedBy() == null ||
            articleCreateModel.getCreatedByName() == null ||
            articleCreateModel.getTitle() == null ||
            articleCreateModel.getDescription() == null ||
            articleCreateModel.getContent() == null ||
            articleCreateModel.getAudioContent() == null ||
            articleCreateModel.getAuthor() == null ||
            articleCreateModel.getCategory() == null ||
            articleCreateModel.getThumbnailUrl() == null
        ) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        } else {

            LocalDateTime now = LocalDateTime.now();

            Article articleEntity = new Article(null, now, now, "", "", "", "", articleCreateModel.getTitle(), articleCreateModel.getDescription(), articleCreateModel.getContent(), articleCreateModel.getAudioContent(), articleCreateModel.getAuthor(), myUriParserUtils.getFinalArticleUrl(articleCreateModel.getTitle()), articleCreateModel.getCategory(), articleCreateModel.getThumbnailUrl(), 0);

            Article tmpSaved = articleService.createOne(articleEntity);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            } else {

                // add tag
                List<ArticleTagRel> articleTagRels = new ArrayList<ArticleTagRel>();

                for (String tagId : articleCreateModel.getTags()) {

                    ArticleTagRel tmpz = new ArticleTagRel(null, tagId, tmpSaved.getId());

                    articleTagRels.add(tmpz);
                }

                articleTagRelService.createMulti(articleTagRels);

                entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
            }
        }

        return entity;
    }

    @PutMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> updateOneArticle(@PathVariable("id") String id, @RequestBody ArticleUpdateModel articleUpdateModel) {
        ResponseEntity<Object> entity;

        if(articleUpdateModel.getModifiedBy() == null ||
            articleUpdateModel.getModifiedByName() == null ||
            articleUpdateModel.getTitle() == null ||
            articleUpdateModel.getDescription() == null ||
            articleUpdateModel.getContent() == null ||
            articleUpdateModel.getAudioContent() == null ||
            articleUpdateModel.getCategory() == null ||
            articleUpdateModel.getThumbnailUrl() == null
        ) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        } else {
            Article tmpArticle = articleService.retrieveOne(id);

            if(tmpArticle == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            } else {
                LocalDateTime now = LocalDateTime.now();

                Article articleEntity = new Article(id, tmpArticle.getDateCreated(), now, tmpArticle.getCreatedBy(), tmpArticle.getCreatedByName(), "", "", articleUpdateModel.getTitle(), articleUpdateModel.getDescription(), articleUpdateModel.getContent(), articleUpdateModel.getAudioContent(), tmpArticle.getAuthor(), tmpArticle.getUrl(), articleUpdateModel.getCategory(), articleUpdateModel.getThumbnailUrl(), 0);

                Article tmpSaved = articleService.updateOne(articleEntity);

                if(tmpSaved == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
                } else {

                    // Delete all current tags of article and re-create
                    Long kk = articleTagRelService.deleteAllByArticleId(id);

                    // add tag
                    List<ArticleTagRel> articleTagRels = new ArrayList<ArticleTagRel>();

                    for (String tagId : articleUpdateModel.getTags()) {

                        ArticleTagRel tmpz = new ArticleTagRel(null, tagId, tmpSaved.getId());

                        articleTagRels.add(tmpz);
                    }

                    articleTagRelService.createMulti(articleTagRels);

                    entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
                }
            }
        }

        return entity;
    }

    // For comments

    @GetMapping(
        value = "/{articleId}/comments",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAllCommentsOfArticle(@PathVariable("articleId") String articleId, @RequestParam(value = "hidden", required = false) Boolean hidden) {
        ResponseEntity<Object> entity;

        List<Comment> comments;

        if(hidden == null) {
            comments = commentService.retrieveAllByArticleId(articleId);

            entity = new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            comments = commentService.retrieveAllByArticleIdAndHidden(articleId, hidden ? 1 : 0);

            entity = new ResponseEntity<>(comments, HttpStatus.OK);
        }

        return entity;
    }

    @PostMapping(
        value = "/{articleId}/comments",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createOneCommentOfArticle(@PathVariable("articleId") String articleId, @RequestBody CommentCreateModel commentCreateModel) {
        ResponseEntity<Object> entity;

        if(commentCreateModel.getAuthor() == null ||
            commentCreateModel.getContent() == null
        ) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        } else {
            LocalDateTime now = LocalDateTime.now();

            Comment commentEntity = new Comment(null, now, now, "", "", "", "", commentCreateModel.getAuthor(), articleId, commentCreateModel.getContent(), 0);

            Comment tmpSaved = commentService.createOne(commentEntity);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            } else {
                entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
            }
        }

        return entity;
    }

    @DeleteMapping(
        value = "/{articleId}/comments/{commentId}"
    )
    public ResponseEntity<Object> deleteOneCommentOfArticle(@PathVariable("articleId") String articleId, @PathVariable("commentId") String commentId) {
        ResponseEntity<Object> entity;

        Comment tmpLoad = commentService.retrieveById(commentId);

        if(tmpLoad == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not found\" }", HttpStatus.NOT_FOUND);
        } else {
            if(!tmpLoad.getArticleId().equals(articleId)) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Not found\" }", HttpStatus.NOT_FOUND);
            } else {
                Boolean isSuccess = false;

                isSuccess = commentService.deleteById(commentId);

                if(isSuccess) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Deleted\" }", HttpStatus.OK);
                } else {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Not found\" }", HttpStatus.NOT_FOUND);
                }
            }
        }

        return entity;
    }

    // For Vote - Evaluation

    @GetMapping(
        value = "/{articleId}/vote-state",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveOneUserVoteState(@PathVariable("articleId") String articleId, @RequestParam(value = "username", required = true) String username) {
        ResponseEntity<Object> entity;

        UserVoteState userVoteState = userVoteStateService.retrieveOneByArticleIdAndAuthor(articleId, username);

        if(userVoteState == null) {
            UVSReturnModel uvsReturnModel = new UVSReturnModel(articleId, username, 0);

            entity = new ResponseEntity<>(uvsReturnModel, HttpStatus.OK);
        } else {
            UVSReturnModel uvsReturnModel = new UVSReturnModel(userVoteState.getArticleId(), userVoteState.getAuthor(), userVoteState.getVoteState());

            entity = new ResponseEntity<>(uvsReturnModel, HttpStatus.OK);
        }

        return entity;
    }

    @PutMapping(
        value = "/{articleId}/vote-state",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> updateUserVoteState(@PathVariable("articleId") String articleId, @RequestParam(value = "username", required = true) String username, @RequestBody UVSUpdateModel uvsUpdateModel) {
        ResponseEntity<Object> entity;

        if(uvsUpdateModel.getVoteState() == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Vote state not be empty\" }", HttpStatus.BAD_REQUEST);
        } else if(uvsUpdateModel.getVoteState() < -1 || uvsUpdateModel.getVoteState() > 1) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Vote state is invalid\" }", HttpStatus.BAD_REQUEST);
        } else {
            UserVoteState tmpUVS = userVoteStateService.retrieveOneByArticleIdAndAuthor(articleId, username);

            if(tmpUVS != null) {
                UserVoteState uvsToSave = new UserVoteState(tmpUVS.getId(), null, null, "", "", "", "", articleId, username, uvsUpdateModel.getVoteState());

                UserVoteState tmpSaved = userVoteStateService.updateOne(uvsToSave);

                if(tmpSaved == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed to save\" }", HttpStatus.BAD_REQUEST);
                } else {
                    entity = new ResponseEntity<>(tmpSaved, HttpStatus.OK);
                }
            } else {
                UserVoteState uvsToSave = new UserVoteState(null, null, null, "", "", "", "", articleId, username, uvsUpdateModel.getVoteState());

                UserVoteState tmpSaved = userVoteStateService.createOne(uvsToSave);

                if(tmpSaved == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed to save\" }", HttpStatus.BAD_REQUEST);
                } else {
                    entity = new ResponseEntity<>(tmpSaved, HttpStatus.OK);
                }
            }
        }

        return entity;
    }
}
