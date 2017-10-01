package org.lc.com.ziyuexs.Base;

import android.support.annotation.StringDef;

import org.lc.com.ziyuelibary.utils.AppUtils;
import org.lc.com.ziyuexs.utils.FileUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;




/**
 * Created by Administrator on 2017-9-13.
 */

public class Constant {
    /**
     * 公共基础类
     *
     */
    public static class Base{
        //远程接口访问基础地址
        public final static String BASE_NETWORK_INTERFACE_URL = "http://api.zhuishushenqi.com";
        //远程图片访问基础地址
        public final static String BASE_NETWORK_IMAGE_URL = "http://statics.zhuishushenqi.com";

        public  static String BASE_CACHE_FILE_URL = FileUtils.createRootPath(AppUtils.getAppContext());
    }

    /***
     * 缓存类
     */
    public static class Cache{
        public static String PATH_DATA = Base.BASE_CACHE_FILE_URL + "/cache";
        public static String PATH_TXT = PATH_DATA + "/book/";
        public static String PATH_EPUB = PATH_DATA + "/epub";
        public static String PATH_CHM = PATH_DATA + "/chm";
    }

    /**
     * 文件类
     */
    public static class File{
        /**
         * 文件格式后缀
         */
        public static final String SUFFIX_TXT = ".txt";
        public static final String SUFFIX_PDF = ".pdf";
        public static final String SUFFIX_EPUB = ".epub";
        public static final String SUFFIX_ZIP = ".zip";
        public static final String SUFFIX_CHM = ".chm";
    }

    public static class Theme{
        public static final String ISNIGHT = "isNight";
    }



    @StringDef({
            Gender.MALE,
            Gender.FEMALE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {
        String MALE = "male";

        String FEMALE = "female";
    }
    @StringDef({
            Distillate.ALL,
            Distillate.DISTILLATE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Distillate {
        String ALL = "";

        String DISTILLATE = "true";
    }

    @StringDef({
            SortType.DEFAULT,
            SortType.COMMENT_COUNT,
            SortType.CREATED,
            SortType.HELPFUL
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SortType {
        String DEFAULT = "updated";

        String CREATED = "created";

        String HELPFUL = "helpful";

        String COMMENT_COUNT = "comment-count";
    }

    @StringDef({
            BookType.ALL,
            BookType.XHQH,
            BookType.WXXX,
            BookType.DSYN,
            BookType.LSJS,
            BookType.YXJJ,
            BookType.KHLY,
            BookType.CYJK,
            BookType.HMZC,
            BookType.XDYQ,
            BookType.GDYQ,
            BookType.HXYQ,
            BookType.DMTR
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface BookType {
        String ALL = "all";

        String XHQH = "xhqh";

        String WXXX = "wxxx";

        String DSYN = "dsyn";

        String LSJS = "lsjs";

        String YXJJ = "yxjj";

        String KHLY = "khly";

        String CYJK = "cyjk";

        String HMZC = "hmzc";

        String XDYQ = "xdyq";

        String GDYQ = "gdyq";

        String HXYQ = "hxyq";

        String DMTR = "dmtr";
    }
}


