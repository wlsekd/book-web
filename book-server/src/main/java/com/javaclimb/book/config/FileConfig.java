package com.javaclimb.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源地址定位
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //作者头像定位
        registry.addResourceHandler("/img/authorPic/*").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"authorPic"+System.getProperty("file.separator")
        );
        //书本图像定位
        registry.addResourceHandler("/img/bookPic/*").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"bookPic"+System.getProperty("file.separator")
        );
        //书单图像定位
        registry.addResourceHandler("/img/bookListPic/*").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"bookListPic"+System.getProperty("file.separator")
        );
        //书本资源地址
        registry.addResourceHandler("/book/*").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"book"+System.getProperty("file.separator")
        );
        //用户头像地址
        registry.addResourceHandler("/img/userPic/*").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"userPic"+System.getProperty("file.separator")
        );
    }
}
