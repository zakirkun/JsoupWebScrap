package com.example.myapplication.fragments.manga_fragments.manga_new_releases_mvp;

import android.util.Log;

import com.example.myapplication.models.mangamodels.MangaNewReleaseResultModel;
import com.example.myapplication.networks.JsoupConfig;
import com.google.gson.Gson;

import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MangaNewReleasePresenter {
    private MangaNewReleaseInterface newReleaseInterface;

    public MangaNewReleasePresenter(MangaNewReleaseInterface newReleaseInterface) {
        this.newReleaseInterface = newReleaseInterface;
    }

    public void getNewReleasesMangaData(int pageCount, String newReleasesURL, String hitStatus) {
        passToJsoup(pageCount, newReleasesURL, hitStatus);
//        CloudFlare cf = new CloudFlare(newReleasesURL);
//        cf.setUser_agent("Mozilla/5.0");
//        cf.getCookies(new CloudFlare.cfCallback() {
//            @Override
//            public void onSuccess(List<HttpCookie> cookieList, boolean hasNewUrl, String newUrl) {
//                Log.e("getNewURL?", String.valueOf(hasNewUrl));
//                Map<String, String> cookies = CloudFlare.List2Map(cookieList);
//                if (hasNewUrl) {
//                    passToJsoup(pageCount, newUrl, hitStatus, cookies);
//                    Log.e("NEWURL", newUrl);
//                } else {
//                    passToJsoup(pageCount, newReleasesURL, hitStatus, cookies);
//                }
//            }
//
//            @Override
//            public void onFail(String message) {
//                newReleaseInterface.onGetNewReleasesDataFailed();
//            }
//        });
    }

    private void passToJsoup(int pageCount, String newReleasesURL, String hitStatus) {
        Document doc = JsoupConfig.setInitJsoup(newReleasesURL + "page/" + pageCount + "/", null);
        if (doc != null) {
            Element el;
            Elements newchaptercon = doc.getElementsByClass("utao");
            List<MangaNewReleaseResultModel> mangaNewReleaseResultModelList = new ArrayList<>();
            for (int position = 0; position < newchaptercon.size(); position++) {
                el = newchaptercon.get(position);
                String mangaType = el.getElementsByTag("ul").attr("class");
                String mangaThumbnailBackground = el.getElementsByTag("img").attr("data-src");

                if (StringUtil.isBlank(mangaThumbnailBackground)) {
                    mangaThumbnailBackground = el.getElementsByTag("img").attr("src");
                }

                if (!mangaThumbnailBackground.contains("https")) {
                    mangaThumbnailBackground = "https:" + mangaThumbnailBackground;
                } else if (!mangaThumbnailBackground.contains("http")) {
                    mangaThumbnailBackground = "http:" + mangaThumbnailBackground;
                }

                String mangaTitle = el.getElementsByTag("h3").text();
                String url = el.getElementsByTag("a").attr("href");
                String mangaStatusParameter = el.getElementsByClass("hot").text();
                List<String> chapterReleaseTime = el.getElementsByTag("i").eachText();
                List<String> chapterUrl = el.select("a[href^=https://komikcast.com/chapter/]").eachAttr("href");
                List<String> chapterTitle = el.select("a[href^=https://komikcast.com/chapter/]").eachText();
                MangaNewReleaseResultModel mangaNewReleaseResultModel = new MangaNewReleaseResultModel();
                MangaNewReleaseResultModel.LatestMangaDetailModel mangaDetailModel = new MangaNewReleaseResultModel().new LatestMangaDetailModel();
                if (!mangaStatusParameter.equalsIgnoreCase("Hot") || StringUtil.isBlank(mangaStatusParameter)) {
                    mangaNewReleaseResultModel.setMangaStatus(false);
                } else if (mangaStatusParameter.equalsIgnoreCase("Hot")) {
                    mangaNewReleaseResultModel.setMangaStatus(true);
                }
                mangaNewReleaseResultModel.setMangaType(mangaType);
                mangaNewReleaseResultModel.setMangaTitle(mangaTitle);
                mangaNewReleaseResultModel.setMangaThumb(mangaThumbnailBackground);
                if (url.startsWith("https://komikcast.com/komik/")) {
                    mangaNewReleaseResultModel.setMangaDetailURL(url);
                }
                List<MangaNewReleaseResultModel.LatestMangaDetailModel> latestMangaDetailModelList = new ArrayList<>();
                mangaDetailModel.setChapterReleaseTime(chapterReleaseTime);
                mangaDetailModel.setChapterTitle(chapterTitle);
                mangaDetailModel.setChapterURL(chapterUrl);
                latestMangaDetailModelList.add(mangaDetailModel);
                mangaNewReleaseResultModel.setLatestMangaDetail(latestMangaDetailModelList);
                mangaNewReleaseResultModelList.add(mangaNewReleaseResultModel);
            }
//            for (Element el : newchaptercon) {
//                String mangaType = el.getElementsByTag("ul").attr("class");
//                String mangaThumbnailBackground = el.getElementsByTag("img").attr("data-src");
//
//                if (StringUtil.isBlank(mangaThumbnailBackground)) {
//                    mangaThumbnailBackground = el.getElementsByTag("img").attr("src");
//                }
//
//                if (!mangaThumbnailBackground.contains("https")) {
//                    mangaThumbnailBackground = "https:" + mangaThumbnailBackground;
//                } else if (!mangaThumbnailBackground.contains("http")) {
//                    mangaThumbnailBackground = "http:" + mangaThumbnailBackground;
//                }
//
//                String mangaTitle = el.getElementsByTag("h3").text();
//                String url = el.getElementsByTag("a").attr("href");
//                String mangaStatusParameter = el.getElementsByClass("hot").text();
//                List<String> chapterReleaseTime = el.getElementsByTag("i").eachText();
//                List<String> chapterUrl = el.select("a[href^=https://komikcast.com/chapter/]").eachAttr("href");
//                List<String> chapterTitle = el.select("a[href^=https://komikcast.com/chapter/]").eachText();
//                MangaNewReleaseResultModel mangaNewReleaseResultModel = new MangaNewReleaseResultModel();
//                MangaNewReleaseResultModel.LatestMangaDetailModel mangaDetailModel = new MangaNewReleaseResultModel().new LatestMangaDetailModel();
//                if (!mangaStatusParameter.equalsIgnoreCase("Hot") || StringUtil.isBlank(mangaStatusParameter)) {
//                    mangaNewReleaseResultModel.setMangaStatus(false);
//                } else if (mangaStatusParameter.equalsIgnoreCase("Hot")) {
//                    mangaNewReleaseResultModel.setMangaStatus(true);
//                }
//                mangaNewReleaseResultModel.setMangaType(mangaType);
//                mangaNewReleaseResultModel.setMangaTitle(mangaTitle);
//                mangaNewReleaseResultModel.setMangaThumb(mangaThumbnailBackground);
//                if (url.startsWith("https://komikcast.com/komik/")) {
//                    mangaNewReleaseResultModel.setMangaDetailURL(url);
//                }
//                List<MangaNewReleaseResultModel.LatestMangaDetailModel> latestMangaDetailModelList = new ArrayList<>();
//                mangaDetailModel.setChapterReleaseTime(chapterReleaseTime);
//                mangaDetailModel.setChapterTitle(chapterTitle);
//                mangaDetailModel.setChapterURL(chapterUrl);
//                latestMangaDetailModelList.add(mangaDetailModel);
//                mangaNewReleaseResultModel.setLatestMangaDetail(latestMangaDetailModelList);
//                mangaNewReleaseResultModelList.add(mangaNewReleaseResultModel);
//            }
            List<MangaNewReleaseResultModel> mangaNewReleaseResultModelListAfterCut = new ArrayList<>(mangaNewReleaseResultModelList.subList(9, mangaNewReleaseResultModelList.size()));
            Log.e("resultBeforeCut", new Gson().toJson(mangaNewReleaseResultModelList));
            Log.e("resultAfterCut", new Gson().toJson(mangaNewReleaseResultModelListAfterCut));
            //store data from JSOUP
            newReleaseInterface.onGetNewReleasesDataSuccess(mangaNewReleaseResultModelListAfterCut, hitStatus, null);
        } else {
            newReleaseInterface.onGetNewReleasesDataFailed();
        }
    }

}
