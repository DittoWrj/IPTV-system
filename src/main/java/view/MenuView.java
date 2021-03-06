package view;

import controller.SubscribeController;
import controller.UserController;
import main.Callback;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MenuView extends JFrame {
    Callback callback;
    private Container container = getContentPane();
    private JPanel menuPanel = new JPanel();
    /*按钮部分*/
    private JButton showTvshows = new JButton("已订阅的节目");
    private JButton showChannels = new JButton("已订阅的频道");
    private JButton subTvshows = new JButton("订阅节目");
    private JButton subChannels = new JButton("订阅频道");

    public MenuView(Callback callback) {
        setTitle("欢迎登录网络电视频道订阅系统");
        // 设计窗体大小
        setBounds(400, 200, 600, 500);
        // 添加一块桌布
        container.setLayout(new BorderLayout());
        JLabel loadingLabel = new JLabel("加载中...");
        container.add(loadingLabel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //网络连接获取数据
        this.callback = callback;
        SubscribeController.getInstance().getTvInfo(new Callback() {
            @Override
            public void onEnd() {
                container.removeAll();
                init();
                addListener();
            }
        });
    }

    private void init() {
        menuPanel.setLayout(null);
        JLabel userLabel = new JLabel("当前用户：" + UserController.getInstance().getUserModel().getUserName());
        userLabel.setFont(new Font("宋体", Font.BOLD, 20));
        userLabel.setBounds(50, 35, 200, 20);
        menuPanel.add(userLabel);
        /*按钮部分*/
        Font ButtonFont = new Font("宋体", Font.PLAIN, 18);
        showTvshows.setBounds(165, 100, 250, 30);
        showTvshows.setFont(ButtonFont);
        showChannels.setBounds(165, 160, 250, 30);
        showChannels.setFont(ButtonFont);
        subTvshows.setBounds(165, 220, 250, 30);
        subTvshows.setFont(ButtonFont);
        subChannels.setBounds(165, 280, 250, 30);
        subChannels.setFont(ButtonFont);
        menuPanel.add(showTvshows);
        menuPanel.add(showChannels);
        menuPanel.add(subTvshows);
        menuPanel.add(subChannels);
        container.add(menuPanel, "Center");
    }

    private void addListener() {
        subTvshows.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SubView subView = SubscribeController.getInstance().goToSubShows();
                JFrame jFrame = new JFrame();
                jFrame.setTitle("订阅节目");
                jFrame.setBounds(400, 200, 600, 500);
                jFrame.add(subView, "Center");
                subView.setVisible(true);
                jFrame.setVisible(true);
            }
        });
        /** 查看已订阅的节目信息*/
        showTvshows.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        SubView subView = SubscribeController.getInstance().showSubSHows();
                        JFrame jFrame = new JFrame();
                        jFrame.setTitle("已订阅节目");
                        jFrame.setBounds(400, 200, 600, 500);
                        jFrame.add(subView, "Center");
                        subView.setVisible(true);
                        jFrame.setVisible(true);
                    }
                });

//        /** 查看已订阅的频道信息*/
//        showChannels.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        String s = "已订阅的频道信息如下：\n\n";
//                        s += "频道名称     " + "频道序号  " + "\n";
//                        int channels_num = -1;//订阅的频道个数
//                        for (int j = 0; j < channels_num; j++) {
//                            //if(channels[j].isLiked()==true){
//                            //  s +=channels[j].get_name()+"     "+channels[j].get_cid();
//                            //}
//                            s += "\n";
//                        }
//                        JOptionPane.showMessageDialog(null, s, "查询结果", JOptionPane.CLOSED_OPTION);
//                    }
//                });
    }
}
