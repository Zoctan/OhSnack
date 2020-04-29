package com.zoctan;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 蛇
 *
 * @author Zoctan
 */
public class Snack {
    private Coordinate head;
    private List<Coordinate> body;
    private int bodyLength;

    public Snack() {
        this.body = new ArrayList<>();
    }

    /**
     * 构造器
     *
     * @param headX          头部横坐标
     * @param headY          头部纵坐标
     * @param initBodyLength 身体长度
     * @param direction      初始化方向，比如 Direction.LEFT 代表向左初始化（头部最先初始化，然后是身体）
     * @throws Exception 异常
     */
    public Snack(int headX, int headY, int initBodyLength, Direction direction) throws Exception {
        checkInit(headX, headY, initBodyLength, direction);

        this.body = new ArrayList<>();
        // 设置头部
        this.setHead(headX, headY);
        // 设置身体（吃东西长身体）
        for (int i = 1; i <= initBodyLength; i++) {
            switch (direction) {
                case LEFT:
                    this.eat(headX - i * Data.cellSize, headY);
                    break;
                default:
                case RIGHT:
                    this.eat(headX + i * Data.cellSize, headY);
                    break;
                case UP:
                    this.eat(headX, headY - i * Data.cellSize);
                    break;
                case DOWN:
                    this.eat(headX, headY + i * Data.cellSize);
                    break;
            }
        }
    }

    private void checkInit(int headX, int headY, int initBodyLength, Direction direction) throws Exception {
        if (Data.cellSize >= headX || headX >= Data.mapSize.getX()) {
            throw new Exception(String.format("初始头部横坐标X超出范围：%d~%d", Data.cellSize * 2, Data.mapSize.getX() - Data.cellSize));
        }
        if (Data.cellSize >= headY || headY >= Data.mapSize.getY()) {
            throw new Exception(String.format("初始头部纵坐标Y超出范围：%d~%d", Data.cellSize * 2, Data.mapSize.getY() - Data.cellSize));
        }
        if (initBodyLength < 1) {
            throw new Exception("初始身体太短");
        }
        // 身体 + 头部
        int totalLength = (initBodyLength + 1) * Data.cellSize;
        // 横向初始化
        if (Direction.LEFT == direction || Direction.RIGHT == direction) {
            if (totalLength > Data.mapSize.getX()) {
                throw new Exception("初始总长超出横长");
            }
            if (Direction.LEFT == direction) {
                if ((headX - initBodyLength * Data.cellSize) < Data.cellSize) {
                    throw new Exception("除去头部，初始向左身体长度超出横长");
                }
            } else {
                if ((headX + initBodyLength * Data.cellSize) > Data.mapSize.getX()) {
                    throw new Exception("除去头部，初始向右身体长度超出横长");
                }
            }
        } else {
            // 纵向初始化
            if (totalLength > Data.mapSize.getY()) {
                throw new Exception("初始总长超出纵长");
            }
            if (Direction.UP == direction) {
                if ((headY - initBodyLength * Data.cellSize) < Data.cellSize) {
                    throw new Exception("除去头部，初始向上身体长度超出纵长");
                }
            } else {
                if ((headY + initBodyLength * Data.cellSize) > Data.mapSize.getY()) {
                    throw new Exception("除去头部，初始向下身体长度超出纵长");
                }
            }
        }
    }

    public boolean isEatSelf() {
        for (int i = 0; i < this.getBodyLength(); i++) {
            Coordinate head = this.getHead();
            Coordinate body = this.getBody().get(i);
            if (body.getX() == head.getX() && body.getY() == head.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean isBeatWall() {
        Coordinate head = this.getHead();
        if (head.getX() <= Data.mapStart.getX() - Data.cellSize || head.getY() <= Data.mapStart.getY() - Data.cellSize) {
            return true;
        }
        if (head.getX() >= Data.mapSize.getX() || head.getY() >= Data.headerSize.getY() + Data.mapSize.getY()) {
            return true;
        }
        return false;
    }

    public void eat(int x, int y) {
        this.body.add(new Coordinate(x, y));
        this.bodyLength++;
    }

    public Coordinate getHead() {
        return head;
    }

    public void setHead(int x, int y) {
        this.head = new Coordinate(x, y);
    }

    public void setHead(Coordinate head) {
        this.head = head;
    }

    public List<Coordinate> getBody() {
        return body;
    }

    public void setBody(List<Coordinate> body) {
        this.body = body;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }
}
