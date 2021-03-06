package com.pantehon.webmagic;

import com.pantehon.webmagic.spider.downloader.HtmlUnitDownLoader;
import com.pantehon.webmagic.spider.pageprocessor.WxImagePageProcessor;
import com.pantehon.webmagic.spider.pipeline.FileDownloadPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;

/**
 * Created by pantheon on 2017/3/10.
 */
@SpringBootApplication
public class Application implements CommandLineRunner{

    Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void run(String... strings) throws Exception {

        HtmlUnitDownLoader htmlUnitDownLoader = new HtmlUnitDownLoader();
        htmlUnitDownLoader.setSleepTime(5000);
        htmlUnitDownLoader.setThread(20);

        Spider.create(new WxImagePageProcessor())
                .addUrl("https://image.baidu.com/search/index?ct=201326592&cl=2&st=-1&lm=-1&nc=1&ie=utf-8&tn=baiduimage&ipn=r&rps=1&pv=&fm=rs4&word=2016%E6%9C%80%E7%81%AB%E5%BE%AE%E4%BF%A1%E5%A4%B4%E5%83%8F&oriquery=%E5%BE%AE%E4%BF%A1%E6%89%8B%E7%BB%98%E5%A4%B4%E5%83%8F%E7%AE%80%E7%BA%A6&ofr=%E5%BE%AE%E4%BF%A1%E6%89%8B%E7%BB%98%E5%A4%B4%E5%83%8F%E7%AE%80%E7%BA%A6")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new FileDownloadPipeline("C:\\wxImages"))
//                .addPipeline(new FileDownloadPipeline("C:\\image.baidu.com"))
                .addPipeline(new FilePipeline("C:\\"))
//                .setDownloader(new SeleniumDownloader("C:\\00_Software\\chromedriver_win32\\chromedriver.exe"))
                .setDownloader(htmlUnitDownLoader)
                .thread(5).runAsync();

        logger.info("com.pantehon.webmagic.Application-run():com.pantehon.webmagic.Application start success!");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
