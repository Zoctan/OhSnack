package com.zoctan;

import javax.swing.*;

/**
 * 数据
 *
 * @author Zoctan
 */
public class Data {
    public static boolean isStart = false;
    public static boolean isFail = false;
    public static boolean isDebug = false;
    public static int score;
    public static Snack snack;
    public static Direction snackDirection;
    public static Direction moveDirection;
    public static Coordinate food = new Coordinate();

    /**
     * 应用程序名称
     */
    public static String appName = "贪吃的你";

    /**
     * 每秒多少帧，越大动画越快
     */
    public static int frames = 7;

    /**
     * 窗口大小
     */
    public static Coordinate windowsSize = new Coordinate(575, 435);

    /**
     * 格子大小
     */
    public static int cellSize = 25;

    /**
     * 顶部在面板中的起始坐标
     */
    public static Coordinate headerStart = new Coordinate(0, 0);

    /**
     * 顶部大小
     */
    public static Coordinate headerSize = new Coordinate(windowsSize.getX(), 75);

    /**
     * 网在面板中的起始坐标
     */
    public static Coordinate mapStart = new Coordinate(0, headerSize.getY());

    /**
     * 网大小
     */
    public static Coordinate mapSize = new Coordinate(windowsSize.getX(), windowsSize.getY() - headerSize.getY() - cellSize - 10);

    /**
     * 资源文件夹
     */
    public static String resourceUrl = "/resource/";

    /**
     * 角色资源文件夹
     */
    public static String roleUrl = resourceUrl + "role/dog/";

    /**
     * 背景音乐
     */
    public static String backgroundMusicUrl = resourceUrl + "bg.mp3";

    /**
     * 图标
     */
    public static ImageIcon icon = new ImageIcon(Data.class.getResource(resourceUrl + "icon.png"));

    /**
     * 顶部栏
     */
    public static ImageIcon headerIcon = getIcon("header.png");

    /**
     * 头部
     */
    public static ImageIcon upHeadIcon = getIcon("upHead.png");
    public static ImageIcon downHeadIcon = getIcon("downHead.png");
    public static ImageIcon leftHeadIcon = getIcon("leftHead.png");
    public static ImageIcon rightHeadIcon = getIcon("rightHead.png");

    /**
     * 身体
     */
    public static ImageIcon bodyIcon = getIcon("body.png");

    /**
     * 食物
     */
    public static ImageIcon foodIcon = getIcon("food.png");

    public static ImageIcon getIcon(String url) {
        return new ImageIcon(Data.class.getResource(roleUrl + url));
    }
}
