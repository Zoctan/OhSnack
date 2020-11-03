package com.zoctan;

import javax.swing.*;
import java.io.Serializable;

/**
 * 数据
 *
 * @author Zoctan
 */
public class Data implements Serializable {
    public boolean isStart = false;
    public boolean isFail = false;
    public boolean isDebug = false;
    public int score;
    public Snack snack;
    public Direction snackDirection;
    public Direction moveDirection;
    public Coordinate food = new Coordinate();

    /**
     * 应用程序名称
     */
    public String appName = "贪吃的你";

    /**
     * 每秒多少帧，越大动画越快
     */
    public int frames = 7;

    /**
     * 窗口大小
     */
    public Coordinate windowsSize = new Coordinate(575, 435);

    /**
     * 格子大小
     */
    public int cellSize = 25;

    /**
     * 顶部在面板中的起始坐标
     */
    public Coordinate headerStart = new Coordinate(0, 0);

    /**
     * 顶部大小
     */
    public Coordinate headerSize = new Coordinate(this.windowsSize.getX(), 75);

    /**
     * 网在面板中的起始坐标
     */
    public Coordinate mapStart = new Coordinate(0, this.headerSize.getY());

    /**
     * 网大小
     */
    public Coordinate mapSize = new Coordinate(this.windowsSize.getX(), this.windowsSize.getY() - this.headerSize.getY() - this.cellSize - 10);

    /**
     * 资源文件夹
     */
    public String resourceUrl = "/resource/";

    /**
     * 角色资源文件夹
     */
    public String roleUrl = this.resourceUrl + "role/dog/";

    /**
     * 背景音乐
     */
    public String backgroundMusicUrl = this.resourceUrl + "bg.wav";

    /**
     * 图标
     */
    public ImageIcon icon = new ImageIcon(Data.class.getResource(this.resourceUrl + "icon.png"));

    /**
     * 顶部栏
     */
    public ImageIcon headerIcon = this.getIcon("header.png");

    /**
     * 头部
     */
    public ImageIcon upHeadIcon = this.getIcon("upHead.png");
    public ImageIcon downHeadIcon = this.getIcon("downHead.png");
    public ImageIcon leftHeadIcon = this.getIcon("leftHead.png");
    public ImageIcon rightHeadIcon = this.getIcon("rightHead.png");

    /**
     * 身体
     */
    public ImageIcon bodyIcon = this.getIcon("body.png");

    /**
     * 食物
     */
    public ImageIcon foodIcon = this.getIcon("food.png");

    public ImageIcon getIcon(String url) {
        return new ImageIcon(Data.class.getResource(this.roleUrl + url));
    }
}
