package org.lc.com.ziyuexs.network;

import org.lc.com.ziyuexs.Bean.AutoComplete;
import org.lc.com.ziyuexs.Bean.BookDetail;
import org.lc.com.ziyuexs.Bean.BookHelp;
import org.lc.com.ziyuexs.Bean.BookHelpList;
import org.lc.com.ziyuexs.Bean.BookListDetail;
import org.lc.com.ziyuexs.Bean.BookListTags;
import org.lc.com.ziyuexs.Bean.BookLists;
import org.lc.com.ziyuexs.Bean.BookMixAToc;
import org.lc.com.ziyuexs.Bean.BookRead;
import org.lc.com.ziyuexs.Bean.BookReview;
import org.lc.com.ziyuexs.Bean.BookReviewList;
import org.lc.com.ziyuexs.Bean.BookSource;
import org.lc.com.ziyuexs.Bean.BooksByCats;
import org.lc.com.ziyuexs.Bean.BooksByTag;
import org.lc.com.ziyuexs.Bean.CategoryList;
import org.lc.com.ziyuexs.Bean.CategoryListLv2;
import org.lc.com.ziyuexs.Bean.ChapterRead;
import org.lc.com.ziyuexs.Bean.CommentList;
import org.lc.com.ziyuexs.Bean.DiscussionList;
import org.lc.com.ziyuexs.Bean.Disscussion;
import org.lc.com.ziyuexs.Bean.HotReview;
import org.lc.com.ziyuexs.Bean.HotWord;
import org.lc.com.ziyuexs.Bean.PostCount;
import org.lc.com.ziyuexs.Bean.RankingList;
import org.lc.com.ziyuexs.Bean.Rankings;
import org.lc.com.ziyuexs.Bean.Recommend;
import org.lc.com.ziyuexs.Bean.RecommendBookList;
import org.lc.com.ziyuexs.Bean.SearchDetail;
import org.lc.com.ziyuexs.Bean.user.Following;
import org.lc.com.ziyuexs.Bean.user.Login;
import org.lc.com.ziyuexs.Bean.user.LoginReq;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-9-19.
 */

public interface ZiyueApiService {


    @GET("/book/recommend")
    Call<Recommend> getRecomend(@Query("gender") String gender);

    /**
     * 获取正版源(若有) 与 盗版源
     * e.g http://api.zhuishushenqi.com/atoc?view=?&book=?
     *
     * @param view
     * @param book
     * @return
     */
    @GET("/atoc")
    Call<List<BookSource>> getABookSource(@Query("view") String view, @Query("book") String book);

    /**
     * 只能获取正版源
     *
     * @param view
     * @param book
     * @return
     */
    @GET("/btoc")
    Call<List<BookSource>> getBBookSource(@Query("view") String view, @Query("book") String book);

    @GET("/mix-atoc/{bookId}")
    Call<BookMixAToc> getBookMixAToc(@Path("bookId") String bookId, @Query("view") String view);

    @GET("/mix-toc/{bookId}")
    Call<BookRead> getBookRead(@Path("bookId") String bookId);

    @GET("/btoc/{bookId}")
    Call<BookMixAToc> getBookBToc(@Path("bookId") String bookId, @Query("view") String view);

    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Call<ChapterRead> getChapterRead(@Path("url") String url);

    @GET("/post/post-count-by-book")
    Call<PostCount> postCountByBook(@Query("bookId") String bookId);

    @GET("/post/total-count")
    Call<PostCount> postTotalCount(@Query("books") String bookId);

    @GET("/book/hot-word")
    Call<HotWord> getHotWord();

    /**
     * 关键字自动补全
     * e.g http://api.zhuishushenqi.com/book/auto-complete?query=%E5%A8%98
     *
     * @param query
     * @return
     */
    @GET("/book/auto-complete")
    Call<AutoComplete> autoComplete(@Query("query") String query);

    /**
     * 书籍查询
     *
     * @param query
     * @return
     */
    @GET("/book/fuzzy-search")
    Call<SearchDetail> searchBooks(@Query("query") String query);

    /**
     * 通过作者查询书名
     * e.g http://api.zhuishushenqi.com/book/accurate-search?author=辰东
     *
     * @param author
     * @return
     */
    @GET("/book/accurate-search")
    Call<BooksByTag> searchBooksByAuthor(@Query("author") String author);

    /**
     * 热门评论
     *
     * @param book
     * @return
     */
    @GET("/post/review/best-by-book")
    Call<HotReview> getHotReview(@Query("book") String book);

    @GET("/book-list/{bookId}/recommend")
    Call<RecommendBookList> getRecommendBookList(@Path("bookId") String bookId, @Query("limit") String limit);

    @GET("/book/{bookId}")
    Call<BookDetail> getBookDetail(@Path("bookId") String bookId);

    @GET("/book/by-tags")
    Call<BooksByTag> getBooksByTag(@Query("tags") String tags, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取所有排行榜
     *
     * @return
     */
    @GET("/ranking/gender")
    Call<RankingList> getRanking();

    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     *
     * @return
     */
    @GET("/ranking/{rankingId}")
    Call<Rankings> getRanking(@Path("rankingId") String rankingId);

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param limit  20
     * @return
     */
    @GET("/book-list")
    Call<BookLists> getBookLists(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("tag") String tag, @Query("gender") String gender);

    /**
     * 获取主题书单标签列表
     *
     * @return
     */
    @GET("/book-list/tagType")
    Call<BookListTags> getBookListTags();

    /**
     * 获取书单详情
     *
     * @return
     */
    @GET("/book-list/{bookListId}")
    Call<BookListDetail> getBookListDetail(@Path("bookListId") String bookListId);

    /**
     * 获取分类
     *
     * @return
     */
    @GET("/cats/lv2/statistics")
    Call<CategoryList> getCategoryList();

    /**
     * 获取二级分类
     *
     * @return
     */
    @GET("/cats/lv2")
    Call<CategoryListLv2> getCategoryListLv2();

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param limit  50
     * @return
     */
    @GET("/book/by-categories")
    Call<BooksByCats> getBooksByCats(@Query("gender") String gender, @Query("type") String type, @Query("major") String major, @Query("minor") String minor, @Query("start") int start, @Query("limit") int limit);


    /**
     * 获取综合讨论区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=true
     *
     * @param block      ramble:综合讨论区
     *                   original：原创区
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param type       all
     * @param start      0
     * @param limit      20
     * @param distillate true(精品)
     * @return
     */
    @GET("/post/by-block")
    Call<DiscussionList> getBookDisscussionList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取综合讨论区帖子详情
     *
     * @param disscussionId->_id
     * @return
     */
    @GET("/post/{disscussionId}")
    Call<Disscussion> getBookDisscussionDetail(@Path("disscussionId") String disscussionId);

    /**
     * 获取神评论列表(综合讨论区、书评区、书荒区皆为同一接口)
     *
     * @param disscussionId->_id
     * @return
     */
    @GET("/post/{disscussionId}/comment/best")
    Call<CommentList> getBestComments(@Path("disscussionId") String disscussionId);

    /**
     * 获取综合讨论区帖子详情内的评论列表
     *
     * @param disscussionId->_id
     * @param start              0
     * @param limit              30
     * @return
     */
    @GET("/post/{disscussionId}/comment")
    Call<CommentList> getBookDisscussionComments(@Path("disscussionId") String disscussionId, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书评区帖子列表
     * 全部、全部类型、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、玄幻奇幻、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=xhqh&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   helpful(最有用的)
     *                   comment-count(最多评论)
     * @param type       all(全部类型)、xhqh(玄幻奇幻)、dsyn(都市异能)...
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/review")
    Call<BookReviewList> getBookReviewList(@Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书评区帖子详情
     *
     * @param bookReviewId->_id
     * @return
     */
    @GET("/post/review/{bookReviewId}")
    Call<BookReview> getBookReviewDetail(@Path("bookReviewId") String bookReviewId);

    /**
     * 获取书评区、书荒区帖子详情内的评论列表
     *
     * @param bookReviewId->_id
     * @param start             0
     * @param limit             30
     * @return
     */
    @GET("/post/review/{bookReviewId}/comment")
    Call<CommentList> getBookReviewComments(@Path("bookReviewId") String bookReviewId, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书荒区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     *                   created(最新发布)
     *                   comment-count(最多评论)
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/help")
    Call<BookHelpList> getBookHelpList(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书荒区帖子详情
     *
     * @param helpId->_id
     * @return
     */
    @GET("/post/help/{helpId}")
    Call<BookHelp> getBookHelpDetail(@Path("helpId") String helpId);

    /**
     * 第三方登陆
     *
     * @param //platform_uid
     * @param //platform_token
     * @param //platform_code  “QQ”
     * @return
     */
    @POST("/user/login")
    Call<Login> login(@Body LoginReq loginReq);

    @GET("/user/followings/{userid}")
    Call<Following> getFollowings(@Path("userid") String userId);

    /**
     * 获取书籍详情讨论列表
     *
     * @param book  bookId
     * @param sort  updated(默认排序)
     *              created(最新发布)
     *              comment-count(最多评论)
     * @param type  normal
     *              vote
     * @param start 0
     * @param limit 20
     * @return
     */
    @GET("/post/by-book")
    Call<DiscussionList> getBookDetailDisscussionList(@Query("book") String book, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书籍详情书评列表
     *
     * @param book  bookId
     * @param sort  updated(默认排序)
     *              created(最新发布)
     *              helpful(最有用的)
     *              comment-count(最多评论)
     * @param start 0
     * @param limit 20
     * @return
     */
    @GET("/post/review/by-book")
    Call<HotReview> getBookDetailReviewList(@Query("book") String book, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit);

    @GET("/post/original")
    Call<DiscussionList> getBookOriginalList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

}
