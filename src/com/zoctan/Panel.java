package com.zoctan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * 面板
 *
 * @author Zoctan
 */
public class Panel extends JPanel implements KeyListener, ActionListener {
    Random random = new Random();

    public Panel() {
        this.init();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    private void init() {
        App.data.score = 0;
        App.data.snackDirection = Direction.LEFT;
        App.data.moveDirection = Direction.RIGHT;
        try {
            App.data.snack = new Snack(App.data.cellSize * 2,
                    App.data.mapStart.getY() + App.data.cellSize,
                    1,
                    App.data.snackDirection);
        } catch (Exception e) {
            showMessageDialog(this, e.getMessage(), App.data.appName, JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.makeFood();
    }

    private void makeFood() {
        App.data.food.setX(App.data.cellSize * (1 + this.random.nextInt(App.data.mapSize.getX() / App.data.cellSize - 1)));
        App.data.food.setY(App.data.mapStart.getY() + App.data.cellSize * this.random.nextInt(App.data.mapSize.getY() / App.data.cellSize - 1));
        if (this.isFoodOnBody()) {
            this.makeFood();
        }
    }

    private boolean isFoodOnBody() {
        for (int i = 0; i < App.data.snack.getBodyLength(); i++) {
            Coordinate body = App.data.snack.getBody().get(i);
            if (body.getX() == App.data.food.getX() && body.getY() == App.data.food.getY()) {
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
        graphics.drawImage(App.data.headerIcon.getImage(), App.data.headerStart.getX(), App.data.headerStart.getY(), App.data.headerSize.getX(), App.data.headerSize.getY(), App.data.headerIcon.getImageObserver());
        // 网格
        graphics.fillRect(App.data.mapStart.getX(), App.data.mapStart.getY(), App.data.mapSize.getX(), App.data.mapSize.getY());

        // 蛇头
        switch (App.data.snackDirection) {
            case LEFT:
                App.data.leftHeadIcon.paintIcon(this, graphics, App.data.snack.getHead().getX(), App.data.snack.getHead().getY());
                break;
            default:
            case RIGHT:
                App.data.rightHeadIcon.paintIcon(this, graphics, App.data.snack.getHead().getX(), App.data.snack.getHead().getY());
                break;
            case UP:
                App.data.upHeadIcon.paintIcon(this, graphics, App.data.snack.getHead().getX(), App.data.snack.getHead().getY());
                break;
            case DOWN:
                App.data.downHeadIcon.paintIcon(this, graphics, App.data.snack.getHead().getX(), App.data.snack.getHead().getY());
                break;
        }
        // 蛇身
        for (int i = 0; i < App.data.snack.getBodyLength(); i++) {
            App.data.bodyIcon.paintIcon(this, graphics, App.data.snack.getBody().get(i).getX(), App.data.snack.getBody().get(i).getY());
        }

        // 食物
        App.data.foodIcon.paintIcon(this, graphics, App.data.food.getX(), App.data.food.getY());

        // 文字提示
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 18));
        if (!App.data.isStart) {
            graphics.setColor(Color.WHITE);
            graphics.drawString("按下空格开始游戏", App.data.mapStart.getX() + App.data.cellSize, App.data.mapStart.getY() + App.data.cellSize - 5);
        }
        if (App.data.isFail) {
            graphics.setColor(Color.RED);
            graphics.drawString("游戏失败，按下空格重新开始", App.data.mapStart.getX() + App.data.cellSize, App.data.mapStart.getY() + App.data.cellSize - 5);
        }
        if (App.data.isDebug) {
            graphics.setFont(new Font("微软雅黑", Font.BOLD, 20));
            graphics.setColor(Color.WHITE);
            graphics.drawString(String.format("小蛇：(%d, %d)", App.data.snack.getHead().getX(), App.data.snack.getHead().getY()), App.data.cellSize, App.data.cellSize);
            graphics.drawString(String.format("食物：(%d, %d)", App.data.food.getX(), App.data.food.getY()), App.data.cellSize, App.data.cellSize * 2);
        }
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // 空格键
        if (KeyEvent.VK_SPACE == keyCode) {
            if (App.data.isFail) {
                App.data.isFail = false;
                this.init();
            } else {
                App.data.isStart = !App.data.isStart;
            }
            // 重绘界面
            this.repaint();
        } else {
            // 方向键
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    App.data.moveDirection = Direction.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    App.data.moveDirection = Direction.RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    App.data.moveDirection = Direction.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    App.data.moveDirection = Direction.DOWN;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (App.data.isStart && !App.data.isFail) {
            this.paintSnack();
            // 移动
            switch (App.data.moveDirection) {
                case UP:
                    App.data.snack.getHead().setY(App.data.snack.getHead().getY() - App.data.cellSize);
                    break;
                case DOWN:
                    App.data.snack.getHead().setY(App.data.snack.getHead().getY() + App.data.cellSize);
                    break;
                case LEFT:
                    App.data.snack.getHead().setX(App.data.snack.getHead().getX() - App.data.cellSize);
                    break;
                default:
                case RIGHT:
                    App.data.snack.getHead().setX(App.data.snack.getHead().getX() + App.data.cellSize);
                    break;
            }
            // 吃自己 || 撞墙
            App.data.isFail = App.data.snack.isEatSelf() || App.data.snack.isBeatWall();

            // 是否吃到食物
            if (App.data.snack.getHead().getX() == App.data.food.getX() && App.data.snack.getHead().getY() == App.data.food.getY()) {
                App.data.snack.eat(App.data.food.getX(), App.data.food.getY());
                this.paintSnack();
                this.makeFood();
                App.data.score++;
                AudioPlayer.play(App.data.eatMusicUrl);
            }

            this.repaint();
        }
    }

    public void paintSnack() {
        // 尾部向前推 2->1->0
        for (int i = App.data.snack.getBodyLength() - 1; i > 0; i--) {
            App.data.snack.getBody().get(i).setX(App.data.snack.getBody().get(i - 1).getX());
            App.data.snack.getBody().get(i).setY(App.data.snack.getBody().get(i - 1).getY());
        }
        // 0->头部
        App.data.snack.getBody().get(0).setX(App.data.snack.getHead().getX());
        App.data.snack.getBody().get(0).setY(App.data.snack.getHead().getY());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
