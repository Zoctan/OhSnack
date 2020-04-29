package com.zoctan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static com.zoctan.Data.*;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * 面板
 *
 * @author Zoctan
 */
public class Panel extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(1000 / frames, this);
    Random random = new Random();

    public Panel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    private void init() {
        score = 0;
        snackDirection = Direction.LEFT;
        moveDirection = Direction.RIGHT;
        try {
            snack = new Snack(cellSize * 2, mapStart.getY() + cellSize, 1, snackDirection);
        } catch (Exception e) {
            showMessageDialog(this, e.getMessage(), appName, JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.makeFood();
    }

    private void makeFood() {
        food.setX(cellSize * (1 + random.nextInt(mapSize.getX() / cellSize - 1)));
        food.setY(mapStart.getY() + cellSize * random.nextInt(mapSize.getY() / cellSize - 1));
        if (isFoodOnBody()) {
            makeFood();
        }
    }

    private boolean isFoodOnBody() {
        for (int i = 0; i < snack.getBodyLength(); i++) {
            Coordinate body = snack.getBody().get(i);
            if (body.getX() == food.getX() && body.getY() == food.getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // 背景颜色
        this.setBackground(Color.WHITE);
        // 顶部图片
        graphics.drawImage(headerIcon.getImage(), headerStart.getX(), headerStart.getY(), headerSize.getX(), headerSize.getY(), headerIcon.getImageObserver());
        // 网格
        graphics.fillRect(mapStart.getX(), mapStart.getY(), mapSize.getX(), mapSize.getY());

        // 蛇头
        switch (snackDirection) {
            case LEFT:
                leftHeadIcon.paintIcon(this, graphics, snack.getHead().getX(), snack.getHead().getY());
                break;
            default:
            case RIGHT:
                rightHeadIcon.paintIcon(this, graphics, snack.getHead().getX(), snack.getHead().getY());
                break;
            case UP:
                upHeadIcon.paintIcon(this, graphics, snack.getHead().getX(), snack.getHead().getY());
                break;
            case DOWN:
                downHeadIcon.paintIcon(this, graphics, snack.getHead().getX(), snack.getHead().getY());
                break;
        }
        // 蛇身
        for (int i = 0; i < snack.getBodyLength(); i++) {
            bodyIcon.paintIcon(this, graphics, snack.getBody().get(i).getX(), snack.getBody().get(i).getY());
        }

        // 食物
        foodIcon.paintIcon(this, graphics, food.getX(), food.getY());

        // 文字提示
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 18));
        if (!isStart) {
            graphics.setColor(Color.WHITE);
            graphics.drawString("按下空格开始游戏", mapStart.getX() + cellSize, mapStart.getY() + cellSize - 5);
        }
        if (isFail) {
            graphics.setColor(Color.RED);
            graphics.drawString("游戏失败，按下空格重新开始", mapStart.getX() + cellSize, mapStart.getY() + cellSize - 5);
        }
        if (isDebug) {
            graphics.setColor(Color.RED);
            graphics.drawString(String.format("小蛇：(%d, %d)", snack.getHead().getX(), snack.getHead().getY()), cellSize, cellSize);
            graphics.drawString(String.format("食物：(%d, %d)", food.getX(), food.getY()), cellSize, cellSize * 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // 空格键
        if (KeyEvent.VK_SPACE == keyCode) {
            if (isFail) {
                isFail = false;
                this.init();
            } else {
                isStart = !isStart;
            }
            // 重绘界面
            this.repaint();
        } else {
            // 方向键
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    moveDirection = Direction.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    moveDirection = Direction.RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    moveDirection = Direction.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    moveDirection = Direction.DOWN;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && !isFail) {
            // 尾部向前推 2->1->0
            for (int i = snack.getBodyLength() - 1; i > 0; i--) {
                snack.getBody().get(i).setX(snack.getBody().get(i - 1).getX());
                snack.getBody().get(i).setY(snack.getBody().get(i - 1).getY());
            }
            // 0->头部
            snack.getBody().get(0).setX(snack.getHead().getX());
            snack.getBody().get(0).setY(snack.getHead().getY());
            // 移动
            switch (moveDirection) {
                case LEFT:
                    snack.getHead().setX(snack.getHead().getX() - cellSize);
                    break;
                default:
                case RIGHT:
                    snack.getHead().setX(snack.getHead().getX() + cellSize);
                    break;
                case UP:
                    snack.getHead().setY(snack.getHead().getY() - cellSize);
                    break;
                case DOWN:
                    snack.getHead().setY(snack.getHead().getY() + cellSize);
                    break;
            }
            // 吃自己 || 撞墙
            isFail = snack.isEatSelf() || snack.isBeatWall();

            // 是否吃到食物
            if (snack.getHead().getX() == food.getX() && snack.getHead().getY() == food.getY()) {
                snack.eat(food.getX(), food.getY());
                this.makeFood();
                score++;
            }

            this.repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
