package com.selesse.marathontrainer;

import com.selesse.marathontrainer.resource.EnglishLanguageResource;
import com.selesse.marathontrainer.resource.LanguageResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 31/05/13
 * Time: 11:32 AM
 */
public class Main implements ActionListener {
    private static LanguageResource resources = new EnglishLanguageResource();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        setLookAndFeel();

        JFrame frame = new JFrame(resources.getProgramName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(createMenuBar());

        Main app = new Main();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private static void setLookAndFeel() {
        final String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(resources.getMenuFileName());
        menu.setMnemonic(resources.getMenuFileMnemonic());

        menuBar.add(menu);

        JMenuItem exitMenuItem = new JMenuItem(resources.getMenuExitName());
        exitMenuItem.setMnemonic(resources.getMenuExitMnemonic());
        menu.add(exitMenuItem);

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Component createComponents() {
        JPanel pane = new JPanel(new GridLayout(0, 1));

        return pane;
    }
}
