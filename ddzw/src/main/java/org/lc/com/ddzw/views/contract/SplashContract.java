package org.lc.com.ddzw.views.contract;

import org.lc.com.ddzw.Base.BaseContract;
import org.lc.com.ddzw.Bean.DiscussionList;

import java.util.List;

/**
 * Created by Administrator on 2017-9-19.
 */

public class SplashContract implements BaseContract {
    interface View extends BaseContract.BaseView {
        void showBookDetailDiscussionList(List<DiscussionList.PostsBean> list, boolean isRefresh);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookDetailDiscussionList(String bookId, String sort, int start, int limit);
    }
}
