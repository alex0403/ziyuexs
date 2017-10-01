package org.lc.com.ddzw.network;

import org.lc.com.ddzw.Base.BaseApi;
import org.lc.com.ddzw.Base.Constant;
import org.lc.com.ddzw.Bean.AutoComplete;
import org.lc.com.ddzw.Bean.BookDetail;
import org.lc.com.ddzw.Bean.BookHelp;
import org.lc.com.ddzw.Bean.BookHelpList;
import org.lc.com.ddzw.Bean.BookListDetail;
import org.lc.com.ddzw.Bean.BookListTags;
import org.lc.com.ddzw.Bean.BookLists;
import org.lc.com.ddzw.Bean.BookMixAToc;
import org.lc.com.ddzw.Bean.BookReview;
import org.lc.com.ddzw.Bean.BookReviewList;
import org.lc.com.ddzw.Bean.BookSource;
import org.lc.com.ddzw.Bean.BooksByCats;
import org.lc.com.ddzw.Bean.BooksByTag;
import org.lc.com.ddzw.Bean.CategoryList;
import org.lc.com.ddzw.Bean.CategoryListLv2;
import org.lc.com.ddzw.Bean.ChapterRead;
import org.lc.com.ddzw.Bean.CommentList;
import org.lc.com.ddzw.Bean.DiscussionList;
import org.lc.com.ddzw.Bean.Disscussion;
import org.lc.com.ddzw.Bean.HotReview;
import org.lc.com.ddzw.Bean.HotWord;
import org.lc.com.ddzw.Bean.RankingList;
import org.lc.com.ddzw.Bean.Rankings;
import org.lc.com.ddzw.Bean.Recommend;
import org.lc.com.ddzw.Bean.RecommendBookList;
import org.lc.com.ddzw.Bean.SearchDetail;
import org.lc.com.ddzw.Bean.user.Login;
import org.lc.com.ddzw.Bean.user.LoginReq;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-9-19.
 */

public class ZiyueApi extends BaseApi{
    public static ZiyueApi instance;

    private ZiyueApiService service;

    public ZiyueApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Base.BASE_NETWORK_INTERFACE_URL)
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(ZiyueApiService.class);
    }

    public static ZiyueApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new ZiyueApi(okHttpClient);
        return instance;
    }

    public Call<Recommend> getRecommend(String gender) {
        return service.getRecomend(gender);
    }

    public Call<HotWord> getHotWord() {
        return service.getHotWord();
    }

    public Call<AutoComplete> getAutoComplete(String query) {
        return service.autoComplete(query);
    }

    public Call<SearchDetail> getSearchResult(String query) {
        return service.searchBooks(query);
    }

    public Call<BooksByTag> searchBooksByAuthor(String author) {
        return service.searchBooksByAuthor(author);
    }

    public Call<BookDetail> getBookDetail(String bookId) {
        return service.getBookDetail(bookId);
    }

    public Call<HotReview> getHotReview(String book) {
        return service.getHotReview(book);
    }

    public Call<RecommendBookList> getRecommendBookList(String bookId, String limit) {
        return service.getRecommendBookList(bookId, limit);
    }

    public Call<BooksByTag> getBooksByTag(String tags, String start, String limit) {
        return service.getBooksByTag(tags, start, limit);
    }

    public Call<BookMixAToc> getBookMixAToc(String bookId, String view) {
        return service.getBookMixAToc(bookId, view);
    }

    public synchronized Call<ChapterRead> getChapterRead(String url) {
        return service.getChapterRead(url);
    }

    public synchronized Call<List<BookSource>> getBookSource(String view, String book) {
        return service.getABookSource(view, book);
    }

    public Call<RankingList> getRanking() {
        return service.getRanking();
    }

    public Call<Rankings> getRanking(String rankingId) {
        return service.getRanking(rankingId);
    }

    public Call<BookLists> getBookLists(String duration, String sort, String start, String limit, String tag, String gender) {
        return service.getBookLists(duration, sort, start, limit, tag, gender);
    }

    public Call<BookListTags> getBookListTags() {
        return service.getBookListTags();
    }

    public Call<BookListDetail> getBookListDetail(String bookListId) {
        return service.getBookListDetail(bookListId);
    }

    public synchronized Call<CategoryList> getCategoryList() {
        return service.getCategoryList();
    }

    public Call<CategoryListLv2> getCategoryListLv2() {
        return service.getCategoryListLv2();
    }

    public Call<BooksByCats> getBooksByCats(String gender, String type, String major, String minor, int start, @Query("limit") int limit) {
        return service.getBooksByCats(gender, type, major, minor, start, limit);
    }

    public Call<DiscussionList> getBookDisscussionList(String block, String duration, String sort, String type, String start, String limit, String distillate) {
        return service.getBookDisscussionList(block, duration, sort, type, start, limit, distillate);
    }

    public Call<Disscussion> getBookDisscussionDetail(String disscussionId) {
        return service.getBookDisscussionDetail(disscussionId);
    }

    public Call<CommentList> getBestComments(String disscussionId) {
        return service.getBestComments(disscussionId);
    }

    public Call<CommentList> getBookDisscussionComments(String disscussionId, String start, String limit) {
        return service.getBookDisscussionComments(disscussionId, start, limit);
    }

    public Call<BookReviewList> getBookReviewList(String duration, String sort, String type, String start, String limit, String distillate) {
        return service.getBookReviewList(duration, sort, type, start, limit, distillate);
    }

    public Call<BookReview> getBookReviewDetail(String bookReviewId) {
        return service.getBookReviewDetail(bookReviewId);
    }

    public Call<CommentList> getBookReviewComments(String bookReviewId, String start, String limit) {
        return service.getBookReviewComments(bookReviewId, start, limit);
    }

    public Call<BookHelpList> getBookHelpList(String duration, String sort, String start, String limit, String distillate) {
        return service.getBookHelpList(duration, sort, start, limit, distillate);
    }

    public Call<BookHelp> getBookHelpDetail(String helpId) {
        return service.getBookHelpDetail(helpId);
    }

    public Call<Login> login(String platform_uid, String platform_token, String platform_code) {
        LoginReq loginReq = new LoginReq();
        loginReq.platform_code = platform_code;
        loginReq.platform_token = platform_token;
        loginReq.platform_uid = platform_uid;
        return service.login(loginReq);
    }

    public Call<DiscussionList> getBookDetailDisscussionList(String book, String sort, String type, String start, String limit) {
        return service.getBookDetailDisscussionList(book, sort, type, start, limit);
    }

    public Call<HotReview> getBookDetailReviewList(String book, String sort, String start, String limit) {
        return service.getBookDetailReviewList(book, sort, start, limit);
    }

    public Call<DiscussionList> getGirlBookDisscussionList(String block, String duration, String sort, String type, String start, String limit, String distillate) {
        return service.getBookDisscussionList(block, duration, sort, type, start, limit, distillate);
    }
}
